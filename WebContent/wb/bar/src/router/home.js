import Vue from 'vue'
import Router from 'vue-router'

Vue.use = function (Router) {

}
Vue.use(Router)

export default[
    {
      path: 'user',
      name: 'user',
      component: () => import('../view/admin/userManage/index')
    },
    {
      path: 'computer',
      name: 'computer',
      component: () => import('../view/admin/computerManage/index')
    }
    ]
