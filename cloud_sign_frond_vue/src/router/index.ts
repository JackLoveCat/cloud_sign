import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, _from, next) => {
  // 路由中设置的needLogin字段就在to当中
  if (window.localStorage.data) {
    // console.log(to.path) //每次跳转的路径
    if (to.path === '/') {
      // 登录状态下 访问login.vue页面 会跳到index.vue
      next({ path: '/index' });
    } else {
      next();
    }
  } else if (to.path === '/') {
    // 如果是登录页面的话，直接next() -->解决注销后的循环执行bug
    next();
  } else {
    // 否则 跳转到登录页面
    next({ path: '/' });
  }
});

export default router;
