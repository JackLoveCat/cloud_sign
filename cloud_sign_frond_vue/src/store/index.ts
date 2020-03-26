import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false
  },
  getters: {
    isLogin: state => state.isLogin
  },
  mutations: {
    login(state) {
      state.isLogin = true;
    },
    logOut(state) {
      state.isLogin = false;
    }
  },
  actions: {},
  modules: {}
});
