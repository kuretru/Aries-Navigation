export default [
  {
    path: '/users',
    routes: [
      { component: './User/Login', layout: false, name: '登录', path: '/users/login' },
      { component: './User/Callback', layout: false, name: '回调', path: '/users/login/callback' },
    ],
  },
  { component: './Welcome', icon: 'smile', name: '欢迎', path: '/welcome' },
  {
    component: './Index/WebIndex',
    hideInMenu: true,
    headerRender: false,
    menuRender: false,
    name: '主页',
    path: '/',
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
  { component: './404' },
];
