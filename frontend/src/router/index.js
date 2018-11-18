import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from '@/components/LandingPage';
import login from '@/components/user/login';
import bbs_detail from '@/components/bbs/bbs_detail'
import bbs_write from '@/components/bbs/bbs_write'

import not_found from '@/components/error/not_found'
import store from '../store'

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
  if (store.getters.isAuthenticated) {
    return next();
  }

  next('/login')
}


export default new Router({
  routes: [
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage,
      beforeEnter: requireAuth()
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/bbs/write',
      name: 'bbs_write',
      component: bbs_write,
      beforeEnter: requireAuth()
    },
    {
      path: '/bbs/:articleId',
      name: 'bbs_detail',
      component: bbs_detail,
      beforeEnter: requireAuth()
    },
    {
      path: '*',
      name: 'not_found',
      component: not_found
    }
  ]
})

