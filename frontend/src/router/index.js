import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from 'SrcRoot/components/Dashboard';


import login from 'SrcRoot/components/user/login';
import bbs_detail from 'SrcRoot/components/bbs/bbs_detail'
import bbs_write from 'SrcRoot/components/bbs/bbs_write'
import bbs_modify from 'SrcRoot/components/bbs/bbs_modify'

import admin_category from 'SrcRoot/components/admin/category'
import admin_user_manage from 'SrcRoot/components/admin/userManage'

import not_found from 'SrcRoot/components/error/not_found'
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
      path: '/admin/userManage',
      name: 'admin_user_manage',
      component: admin_user_manage,
      beforeEnter: requireAuthAdmin()
    },
    {
      path: '*',
      name: 'not_found',
      component: not_found
    }
  ]
})

