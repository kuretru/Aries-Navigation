export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { path: '/user', routes: [{ name: '登录', path: '/user/login', component: './user/Login' }] },
      { component: './404' },
    ],
  },
  {
    component: './Welcome',
    icon: 'smile',
    name: '欢迎',
    path: '/welcome',
  },
  {
    icon: 'fileText',
    name: '内容管理',
    routes: [
      { component: './Web/Tag', name: '标签管理(一级菜单)', path: '/admin/web/tags' },
      { component: './Web/Category', name: '分类管理(二级菜单)', path: '/admin/web/categories' },
      { component: './Web/Site', name: '站点管理', path: '/admin/web/sites' },
    ],
  },
  { path: '/', redirect: '/welcome' },
  { component: './404' },
];
