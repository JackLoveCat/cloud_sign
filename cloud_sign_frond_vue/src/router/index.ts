import Vue from "vue";
import VueRouter, { Route } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../components/page/Login.vue";
import store from "../store/index";
import Register from "../components/page/Register.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      title: "首页"
    }
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      title: "登录",
      showFoot: true
    }
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: {
      title: "注册",
      showFoot: true
    }
  },
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

// 全局路由守卫
router.beforeEach((to: Route, from: Route, next: Function) => {
  console.log("cs_guards" + to.name + "  " + from.name);
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  if (store.state.isLogin) {
    if (to.path == "/login" || to.path == "/register") {
      next("/home");
    } else {
      next();
    }
  } else {
    if (to.path == "/login" || to.path == "/register") {
      next();
    } else {
      next("/login");
    }
  }
});
export default router;
