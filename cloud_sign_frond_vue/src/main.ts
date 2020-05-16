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

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
