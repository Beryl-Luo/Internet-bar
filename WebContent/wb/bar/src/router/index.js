import Vue from 'vue'
import Router from 'vue-router'
import home from './home'

Vue.use(Router)
const router = new Router({
  router: [
    {
      path: 'test',
      component: () => import('../packages/components/HelloWorld')
    },
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
    }
  ]
})
export default router
