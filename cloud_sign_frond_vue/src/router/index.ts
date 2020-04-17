import Vue from "vue";
import VueRouter, { Route } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../components/page/Login.vue";
import store from "../store/index";
import Register from "../components/page/Register.vue";
import MyClass from "../views/MyClass.vue";
import Sign from "../views/Sign.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      title: "首页",
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      title: "登录",
      showFoot: true,
    },
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: {
      title: "注册",
      showFoot: false,
    },
  },
  {
    path: "/myclass",
    name: "MyClass",
    component: MyClass,
    meta: {
      title: "我的班课",
      showFoot: false,
    },
  },
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/base/About.vue"),
    meta: {
      title: "关于",
      showFoot: false,
    },
  },
  {
    path: "/sign",
    name: "Sign",
    component: Sign,
    meta: {
      title: "签到",
      showFoot: false,
    },
  },

  {
    path: "/404",
    name: "notFound",
    component: () =>
      import(/* webpackChunkName: "404" */ "../views/base/404.vue"),
  },
  {
    path: "*", // 此处需特别注意置于最底部
    redirect: "/404",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// 全局路由守卫
router.beforeEach((to: Route, from: Route, next: Function) => {
  console.log("cs_guards" + to.name + "  " + from.name);
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  if (store.getters["User/isLogin"]) {
    if (to.path == "/login" || to.path == "/register") {
      next("/");
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
