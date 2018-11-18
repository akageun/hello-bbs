import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../router'

Vue.use(Vuex)

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}

//enhanceAccessToeken()

function auth() {

  const token = localStorage.getItem('token');
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

export default new Vuex.Store({
  state: {
    accessToken: null
  },
  getters: {
    isAuthenticated(state) {
      state.accessToken = state.accessToken || localStorage.getItem('token');
      return state.accessToken
    }
  },
  mutations: {
    LOGIN(state, {data}) {
      console.log('accessToken ', data)

      state.accessToken = data
      localStorage.setItem('token', data)
    },
    LOGOUT(state) {
      state.accessToken = null
      localStorage.removeItem('token')
    }
  },
  actions: {
    LOGIN({commit}, {userId, passWd}) {
      let form = new FormData();
      form.append('userId', userId);
      form.append('passWd', passWd);

      return axios.post('/api/user/v1/login', form)
        .then(({data}) => {
          console.log('success : ', data);

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

      return axios.get('/api/bbs/v1/article', auth())
        .then(({data}) => {
          console.log('success : ', data);
          return data;
        }).catch(({data}) => {
          console.log("에러 ", data);
        });
    },

    LOGOUT({commit}) {
      commit('LOGOUT')
      router.push('/login')
    },
  }
})
