/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-03 22:30:43
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-04 00:05:32
 */

import { MutationTree, ActionTree, GetterTree, Module } from "vuex";
import { IndexState } from "./index";

//接口
export interface User {
  isLogin: boolean;
}

//重新赋值
const state: User = {
  isLogin: localStorage.getItem("user") !== null,
};

const mutations: MutationTree<User> = {
  login(state, userInfo) {
    console.log("user_login");
    console.log(state.isLogin);
  },
};
const getters: GetterTree<User, IndexState> = {
  isLogin: (state: User) => state.isLogin,
};
const actions: ActionTree<User, IndexState> = {
  login({ commit, state }, userInfo) {
    // commit("login", userInfo);
    localStorage.setItem("user", userInfo);
    state.isLogin = true;
  },
};

export const User: Module<User, IndexState> = {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
