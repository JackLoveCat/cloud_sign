/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-03 22:30:43
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-04 16:41:43
 */

import { MutationTree, ActionTree, GetterTree, Module } from "vuex";
import { IndexState } from "./index";

//接口
export interface User {
  isLogin: boolean;
  userInfo:
    | undefined
    | {
        userId: number;
        userName: string;
        nickName: string;
        avatar: string;
        email: string;
        phonenumber: string;
        uniacadaId?: number;
        roleid: number;
        roleName: string;
      };
}
export function getUserId(): number | undefined {
  const user = localStorage.getItem("user");
  if (user === null) {
    return undefined;
  }
  const obj = JSON.parse(user);
  if (obj === undefined || obj === null || !obj.userId) {
    return undefined;
  }
  return obj.userId;
}
export function getToken(): string | undefined {
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
export function initLogin(): User {
  const token = getToken();
  if (token === undefined) {
    return { isLogin: false, userInfo: undefined };
  }
  const user = localStorage.getItem("user");
  if (user === null) {
    return { isLogin: false, userInfo: undefined };
  }
  const userJson = JSON.parse(user);
  if (userJson === undefined || userJson === null) {
    return { isLogin: false, userInfo: undefined };
  }
  return { isLogin: true, userInfo: userJson };
}
//重新赋值
const state: User = initLogin();

const mutations: MutationTree<User> = {
  login(state, userInfo) {  
    state.isLogin = true;
    state.userInfo = userInfo;
  },
  logout(state, userInfo) {
    state.userInfo = undefined;
    state.isLogin = false;
  },
};
const getters: GetterTree<User, IndexState> = {
  isLogin: (state: User) => state.isLogin,
  getUserInfo: (state: User) => state.userInfo,
};
const actions: ActionTree<User, IndexState> = {
  login({ commit, state }, token) {
    localStorage.setItem(
      "token",
      JSON.stringify({ token: token, expire: new Date().getTime() })
    );
    state.isLogin = true;
  },
  logout({ commit, state }) {
    state.isLogin = false;
    state.userInfo = undefined;
    localStorage.removeItem("token");
    localStorage.removeItem("user");
  },
  saveUserInfo({ commit, state }, userInfo) {
    state.userInfo = {
      userId: userInfo.userId,
      uniacadaId: userInfo.uniacadaId,
      userName: userInfo.userName,
      nickName: userInfo.nickName,
      email: userInfo.email,
      phonenumber: userInfo.phonenumber,
      avatar: userInfo.avatar,
      roleid: userInfo.roles[0].roleId,
      roleName: userInfo.roles[0].roleName,
    };
    localStorage.setItem("user", JSON.stringify(state.userInfo));
  },
};

export const User: Module<User, IndexState> = {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
