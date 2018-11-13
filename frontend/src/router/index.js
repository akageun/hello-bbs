import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from '@/components/LandingPage'
import bbs_list from '@/components/bbs/bbs_list'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage
    },
    {
      path: '/bbs',
      name: 'bbs_list',
      component: bbs_list
    },
    {
      path: '*',
      name: 'LandingPage',
      component: LandingPage
    }
  ]
})
