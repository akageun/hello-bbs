import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../router'

Vue.use(Vuex)

const jwtTokenName = "tk";

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}

//enhanceAccessToeken()

function auth() {

  const token = localStorage.getItem(jwtTokenName);
  if (token === undefined) {
    return '';
  }

  return {
    headers:
      {
        Authorization: 'Bearer ' + token
      }
  }
}

function isExpiredTokenCheck(data) {
  if (data.response.status === 401) {
    router.push('/login');
    return;
  }
}

export default new Vuex.Store({
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
    isAuthenticated(state) {
      state.accessToken = state.accessToken || localStorage.getItem(jwtTokenName);
      return state.accessToken
    }
  },
  mutations: {
    LOGIN(state, {data}) {
      console.log('accessToken ', data)

      state.accessToken = data
      localStorage.setItem(jwtTokenName, data)
    },
    LOGOUT(state) {
      state.accessToken = null
      localStorage.removeItem(jwtTokenName)
    },
    GET_BBS_LIST(state, {pagination, resultList}) {
      state.bbs.bbsList = resultList;
      state.bbs.pagination = pagination;

      this.state.pageRange = [];

      for (let i = pagination.firstBlockPageNo; i <= pagination.lastBlockPageNo; i++) {
        this.state.pageRange.push(i);
      }

    }
  },
  actions: {
    LOGIN({commit}, {userId, passWd}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);

      return axios.post('/api/user/v1/login', form)
        .then(({data}) => {
          commit('LOGIN', {data})
          router.push('/')

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

    GET_BBS_LIST({commit}, {}) {
      let tmpParams = {};
      tmpParams['params'] = {
        'pageNumber': this.state.pageIndex
      };

      tmpParams['headers'] = {
        Authorization: 'Bearer ' + localStorage.getItem(jwtTokenName)
      };

      return axios.get('/api/bbs/v1/article', tmpParams)
        .then(data => {
          if (data.status === 200) {
            const pagination = data.data.data.pagination;
            const resultList = data.data.data.resultList;

            commit('GET_BBS_LIST', {pagination, resultList});
          }

        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    },

    GET_BBS({commit}, {articleId}) {

      return axios.get('/api/bbs/v1/article/' + articleId, auth())
        .then(data => {
          return data;
        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    },

    ADD_BBS_ARTICLE({commit}, {title, content, statusCd}) {
      let form = new FormData();
      form.append('title', title);
      form.append('content', content);
      form.append('statusCd', statusCd);

      return axios.post('/api/bbs/v1/article', form, auth())
        .then(data => {
          console.log('data : ', data);
          return data;
        }).catch(error => {
          isExpiredTokenCheck(error);
        });
    }
    ,
    LOGOUT({commit}) {
      commit('LOGOUT')
      router.push('/login')
    }
    ,
  }
})
