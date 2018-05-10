export default[
  {
    path: 'user',
    component: () => import('../view/admin/userManage'),
    children: [
      {
        path: '',
        name: 'userList',
        component: () => import('../view/admin/userManage/list')
      },
      {
        path: 'userAddForm',
        name: 'userAddForm',
        component: () => import('../view/admin/userManage/form')
      },
      {
        path: 'userEditForm/:id',
        name: 'userEditForm',
        component: () => import('../view/admin/userManage/form')
      },
      {
        path: 'userDisplay/:id',
        name: 'userDisplay',
        component: () => import('../view/admin/userManage/display')
      }
    ]
  },
  {
    path: 'computer',
    component: () => import('../view/admin/computerManage'),
    children: [
      {
        path: '',
        name: 'computerList',
        component: () => import('../view/admin/computerManage/list')
      },
      {
        path: 'computerAddForm',
        name: 'computerAddForm',
        component: () => import('../view/admin/computerManage/form')
      },
      {
        path: 'computerEditForm/:id',
        name: 'computerEditForm',
        component: () => import('../view/admin/computerManage/form')
      },
      {
        path: 'computerDisplay/:id',
        name: 'computerDisplay',
        component: () => import('../view/admin/computerManage/display')
      }
    ]
  }
]
