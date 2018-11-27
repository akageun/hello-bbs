import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard';


import login from '@/components/user/login';
import bbs_detail from '@/components/bbs/bbs_detail'
import bbs_write from '@/components/bbs/bbs_write'
import bbs_modify from '@/components/bbs/bbs_modify'

import admin_category from '@/components/admin/category'

import not_found from '@/components/error/not_found'
import store from '../store'

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
  if (store.getters.isAuthenticated) {
    return next();
  }

  next('/login')
}

const requireAuthAdmin = () => (to, from, next) => {
  if (store.getters.isAdminStorage) {
    return next();
  }

  next('/login')
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard,
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
      path: '/bbs/write/:articleId',
      name: 'bbs_modify',
      component: bbs_modify,
      beforeEnter: requireAuth()
    },
    {
      path: '/bbs/:articleId',
      name: 'bbs_detail',
      component: bbs_detail,
      beforeEnter: requireAuth()
    },
    {
      path: '/admin/category',
      name: 'admin_category',
      component: admin_category,
      beforeEnter: requireAuthAdmin()
    },
    {
      path: '*',
      name: 'not_found',
      component: not_found
    }
  ]
})

