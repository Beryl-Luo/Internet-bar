import Vue from 'vue'
import Router from 'vue-router'
import home from './home'
Vue.use(Router)
const router = new Router({
  routes: [
    {
      path: '/admin',
      component: () => import('../packages/ui/container'),
      children: [
        ...home
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../view/admin/login/login')
    },
    {
      path: '/',
      name: 'netPlay',
      component: () => import('../view/client/netPlay')
    },
    {
      path: '/netPlayLogin/:id',
      name: 'netPlayLogin',
      component: () => import('../view/client/netPlay/login')
    },
    {
      path: '/netPlayMessage/:id',
      name: 'netPlayMessage',
      component: () => import('../view/client/netPlay/message')
    }
  ]
})
export default router
