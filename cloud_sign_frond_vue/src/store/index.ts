import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import { IndexState } from "./model/index";
import { User } from "./model/User";
Vue.use(Vuex);
const store: StoreOptions<IndexState> = {
  modules: {
    User,
  },
  state: {
    version: "1.0.0",
  },
};
export default new Vuex.Store<IndexState>(store);
