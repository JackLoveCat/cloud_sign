import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import "@/less/weui.less";
import Toast from "./components/base/toast/index";
import VueClipboard from "vue-clipboard2";

Vue.config.productionTip = false;

Vue.use(Toast);
Vue.use(VueClipboard);
//系统错误捕获
const errorHandler = (error: Error, vm: Vue, info?: string) => {
  console.error("抛出全局异常");
  console.error(error);
};

Vue.config.errorHandler = errorHandler;
const vm = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
Vue.prototype.$throw = (error: Error) => errorHandler(error, vm);
