export default [
  {
    path: '/users',
    layout: false,
    routes: [
      {
        path: '/users',
        routes: [
          { component: './User/Login', name: '登录', path: '/users/login' },
          { component: './User/Callback', name: '回调', path: '/users/login/callback' },
        ],
      },
      { component: './404' },
    ],
  },
  { component: './Welcome', icon: 'smile', path: '/welcome', name: '欢迎' },
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
