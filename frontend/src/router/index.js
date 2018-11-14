import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from '@/components/LandingPage';
import login from '@/components/user/login';
import bbs_list from '@/components/bbs/bbs_list'
import bbs_detail from '@/components/bbs/bbs_detail'
import bbs_write from '@/components/bbs/bbs_write'

import not_found from '@/components/error/not_found'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/bbs',
      name: 'bbs_list',
      component: bbs_list
    },
    {
      path: '/bbs/write',
      name: 'bbs_write',
      component: bbs_write
    },
    {
      path: '/bbs/:articleId',
      name: 'bbs_detail',
      component: bbs_detail
    },
    {
      path: '*',
      name: 'not_found',
      component: not_found
    }
  ]
})

