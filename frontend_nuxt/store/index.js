import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import cookie from 'js-cookie';

const cookieparser = process.server ? require('cookieparser') : undefined

Vue.use(Vuex);

const BASE_URL = 'http://localhost:9987';

const store = () => new Vuex.Store({
  state: {

    accessToken: null,
    bbs: {
      bbsList: [],
      pagination: {}
    },
    pageIndex: 0,
    pageRange: []
  },
  getters: {
    isAuthenticated(store) {
      console.log(store.state);
      return store.state.accessToken;
    }
  },
  mutations: {
    LOGIN(state, {data}) {
      this.state.accessToken = data.data;
      cookie.set('key', data.data);
    },
    LOGOUT(state) {
      console.log("3")
      cookie.remove('key');
      this.state.accessToken = null;

      this.$router.push("/login");
    },
    GET_BBS_LIST(state, {pagination, resultList}) {
      this.state.bbs.bbsList = resultList;
      this.state.bbs.pagination = pagination;

      this.state.pageRange = [];

      for (let i = pagination.firstBlockPageNo; i <= pagination.lastBlockPageNo; i++) {
        this.state.pageRange.push(i);
      }

    },
    setAuth(state, auth) {
      this.state.accessToken = auth
    }
  },
  actions: {
    nuxtServerInit({commit}, {req}) {
      let auth = null
      if (req.headers.cookie) {
        const parsed = cookieparser.parse(req.headers.cookie)
        try {
          auth = parsed.key
        } catch (err) {
        }

        if (auth !== null) {
          commit('setAuth', auth)
        }
      }

    },
    LOGIN({commit}, {userId, passWd}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);

      return axios.post(BASE_URL + '/api/user/v1/login', form)
        .then(({data}) => {
          commit('LOGIN', {data});

          this.$router.push("/");

        }).catch(({data}) => {
          console.log("에러 ", data);
        });
    },
    SIGNUP({commit}, {userId, passWd, passWdConfirm}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);
      form.append('confirmPassWd', passWdConfirm);

      return axios.post(BASE_URL + '/api/user/v1/signup', form)
        .then(({data}) => {
          return data;
        }).catch(({data}) => {
          console.log("에러 ", data);
        });
    },
    LOGOUT({commit}) {
      console.log("1");
      commit('LOGOUT');
      console.log("2")
    },

    GET_BBS_LIST({commit}, {}) {

      let tmpParams = {};
      tmpParams['params'] = {'pageNumber': this.state.pageIndex};

      tmpParams['headers'] = {
        Authorization: 'Bearer ' + this.state.accessToken
      };


      return axios.get(BASE_URL + '/api/bbs/v1/article', tmpParams)
        .then(data => {
          if (data.status === 200) {
            const pagination = data.data.data.pagination;
            const resultList = data.data.data.resultList;

            commit('GET_BBS_LIST', {pagination, resultList});
          }

        }).catch(error => {
          if (error.response.status === 401) {
            this.$router.push('/login');
          }
        });
    },

    GET_BBS({commit}, {articleId}) {

      let tmpParams = {};
      tmpParams['headers'] = {
        Authorization: 'Bearer ' + this.state.accessToken
      };

      return axios.get(BASE_URL + '/api/bbs/v1/article/' + articleId, tmpParams)
        .then(data => {
          return data;
        }).catch(error => {
          if (error.response.status === 401) {
            this.$router.push('/login');
            return;
          }
        });
    },

    ADD_BBS_ARTICLE({commit}, {title, content, statusCd}) {
      let form = new FormData();
      form.append('title', title);
      form.append('content', content);
      form.append('statusCd', statusCd);

      let tmpParams = {};
      tmpParams['headers'] = {
        Authorization: 'Bearer ' + this.state.accessToken
      };

      return axios.post(BASE_URL + '/api/bbs/v1/article', form, tmpParams)
        .then(data => {
          console.log('data : ', data);
          return data;
        }).catch(error => {
          if (error.response.status === 401) {
            this.$router.push('/login');
            return;
          }
        });
    },
    MODIFY_BBS_ARTICLE({commit}, {articleId, title, content, statusCd}) {
      let form = new FormData();
      form.append('title', title);
      form.append('content', content);
      form.append('statusCd', statusCd);

      let tmpParams = {};
      tmpParams['headers'] = {
        Authorization: 'Bearer ' + this.state.accessToken
      };

      return axios.put(BASE_URL + '/api/bbs/v1/article/' + articleId, form, tmpParams)
        .then(data => {
          return data;
        }).catch(error => {
          if (error.response.status === 401) {
            this.$router.push('/login');
            return;
          }
        });
    }
  }
})

export default store
