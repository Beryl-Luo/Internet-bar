export default[
  {
    path: '/',
    component: () => import('../view/admin/userManage/user')
  },
  {
    path: '/computer',
    component: () => import('../view/admin/computerManage/computer')
  }
]
