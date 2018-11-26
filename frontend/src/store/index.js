import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../router'

Vue.use(Vuex);

const jwtTokenName = "tk";

function isExpiredTokenCheck(data) {
  if (data.response.status === 401) {
    router.push('/login');
    return;
  }
}

function getParamsWithAuth(params) {
  let tmpParams = {};
  if (params !== undefined) {
    tmpParams['params'] = params;
  }

  tmpParams['headers'] = {
    Authorization: 'Bearer ' + localStorage.getItem(jwtTokenName)
  };

  return tmpParams;
}


export default new Vuex.Store({
  state: {
    /*accessToken: null,*/
    bbs: {
      bbsList: [],
      pagination: {}
    },
    pageIndex: 0,
    pageRange: []
  },
  getters: {
    isAuthenticated(state) {
      return localStorage.getItem(jwtTokenName)
    }
  },
  mutations: {
    LOGIN(state, {data}) {
      localStorage.setItem(jwtTokenName, data.data)
    },
    LOGOUT(state) {
      localStorage.removeItem(jwtTokenName)
    }
  },
  actions: {
    LOGIN({commit}, {userId, passWd}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);

      return axios.post('/api/user/v1/login', form)
        .then(({data}) => {
          commit('LOGIN', {data});
          router.push("/");

        }).catch(({data}) => {
          console.log("에러 ", data);
        });
    },
    SIGNUP({commit}, {userId, passWd, passWdConfirm}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);
      form.append('confirmPassWd', passWdConfirm);

      return axios.post('/api/user/v1/signup', form)
        .then(({data}) => {
          console.log('success : ', data);
          return data;
        }).catch(({data}) => {
          console.log("에러 ", data);
        });
    },
    LOGOUT({commit}) {
      commit('LOGOUT');
      router.push('/login');
    },

    GET_BBS_LIST({commit}, {}) {
      return axios.get('/api/bbs/v1/article', getParamsWithAuth({'pageNumber': this.state.pageIndex}))
        .then(data => {
          if (data.status === 200) {
            return {
              "pagination": data.data.data.pagination,
              "resultList": data.data.data.resultList
            }
          }

        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    },

    GET_BBS({commit}, {articleId}) {

      return axios.get('/api/bbs/v1/article/' + articleId, getParamsWithAuth())
        .then(data => {
          console.log(data.data.data);
          return data;
        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    },

    ADD_BBS_ARTICLE({commit}, {title, content, statusCd, categoryId}) {
      let form = new FormData();
      form.append('title', title);
      form.append('content', content);
      form.append('statusCd', statusCd);
      form.append('categoryId', categoryId);

      return axios.post('/api/bbs/v1/article', form, getParamsWithAuth())
        .then(data => {
          console.log('data : ', data);
          return data;
        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    },
    MODIFY_BBS_ARTICLE({commit}, {articleId, title, content, statusCd, categoryId}) {
      let form = new FormData();
      form.append('title', title);
      form.append('content', content);
      form.append('statusCd', statusCd);
      form.append('categoryId', categoryId);

      return axios.put('/api/bbs/v1/article/' + articleId, form, getParamsWithAuth())
        .then(data => {
          return data;
        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    }
  }
})
