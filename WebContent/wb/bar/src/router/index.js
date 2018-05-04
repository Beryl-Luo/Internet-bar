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
      path: '/',
      name: 'login',
      component: () => import('../view/admin/login/login')
    }
  ]
})
export default router
