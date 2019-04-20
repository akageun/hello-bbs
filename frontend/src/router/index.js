import Vue from 'vue'
import Router from 'vue-router'

import dashboard from 'SrcRoot/views/dashboard';
import login from 'SrcRoot/views/user/login';

import adminCategory from 'SrcRoot/views/admin/category'
import adminUserManage from 'SrcRoot/views/admin/userManage'

import bbsDetail from 'SrcRoot/views/bbs/detail'
import bbsWrite from 'SrcRoot/views/bbs/write'
import bbsModify from 'SrcRoot/views/bbs/modify'


import not_found from 'SrcRoot/views/error/not_found'
import store from '../store'

Vue.use(Router);

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
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: dashboard,
      beforeEnter: requireAuth()
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/bbs/write',
      name: 'bbsWrite',
      component: bbsWrite,
      beforeEnter: requireAuth()
    },
    {
      path: '/bbs/write/:articleId',
      name: 'bbs_modify',
      component: bbsModify,
      beforeEnter: requireAuth()
    },
    {
      path: '/bbs/:articleId',
      name: 'detail',
      component: bbsDetail,
      beforeEnter: requireAuth()
    },
    {
      path: '/admin/category',
      name: 'adminCategory',
      component: adminCategory,
      beforeEnter: requireAuthAdmin()
    },
    {
      path: '/admin/userManage',
      name: 'adminUserManage',
      component: adminUserManage,
      beforeEnter: requireAuthAdmin()
    },
    {
      path: '*',
      name: 'not_found',
      component: not_found
    }
  ]
})

