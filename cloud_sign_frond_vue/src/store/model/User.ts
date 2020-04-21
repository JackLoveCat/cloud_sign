/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-03 22:30:43
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-06 10:45:22
 */

import { MutationTree, ActionTree, GetterTree, Module } from "vuex";
import { IndexState } from "./index";

//接口
export interface User {
  isLogin: boolean;
}
export function checkAndGetToen(): string | undefined {
  const token = localStorage.getItem("token");
  if (token === null) {
    return undefined;
  }
  const obj = JSON.parse(token);
  if (obj === undefined || obj === null || !obj.expire || !obj.token) {
    return undefined;
  }
  if (obj.expire + 86400000 - new Date().getTime() < 0) {
    localStorage.removeItem("token");
    return undefined;
  }
  return obj.token;
}
function checkLogin(): boolean {
  return checkAndGetToen() !== undefined;
}
//重新赋值
const state: User = {
  isLogin: checkLogin(),
};

const mutations: MutationTree<User> = {
  login(state, userInfo) {
    console.log("user_login");
    console.log(state.isLogin);
  },
  logout(state, userInfo) {
    console.log("user_login");
    console.log(state.isLogin);
  },
};
const getters: GetterTree<User, IndexState> = {
  isLogin: (state: User) => state.isLogin,
};
const actions: ActionTree<User, IndexState> = {
  login({ commit, state }, token) {
    // commit("login", userInfo);
    localStorage.setItem(
      "token",
      JSON.stringify({ token: token, expire: new Date().getTime() })
    );
    state.isLogin = true;
  },
  logout({ commit, state }) {
    state.isLogin = false;
    localStorage.removeItem("token");
  },
};

export const User: Module<User, IndexState> = {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
