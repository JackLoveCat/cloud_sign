/*
{ AxiosInstance, AxiosRequestConfig, AxiosResponse }
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-22 20:23:12
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-03 22:17:22
 */
import axios from "axios";
import { UserInfo } from "@/types/model/User";
const HOST_DOMAIN = "/apis";

export default class Api {
  static uuid() {
    return "xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx".replace(/[xy]/g, function(c) {
      const r = (Math.random() * 16) | 0,
        v = c == "x" ? r : (r & 0x3) | 0x8;
      return v.toString(16);
    });
  }

  static loginByPhone(userNmae: string, password: string) {
    return axios.post(HOST_DOMAIN + "/loginbyphone", {
      phonenum: userNmae,
      password: password,
      uuid: this.uuid()
    });
  }
  static toQueryString(obj: Map<string, string>) {
    const str = new Array<string>();
    obj.forEach((value, key) => {
      str.push(encodeURIComponent(key) + "=" + encodeURIComponent(value));
    });
    return str.join("&");
  }
  static login(userNmae: string, password: string) {
    // const params = new Map<string, string>();
    // params.set("username", userNmae);
    // params.set("password", password);
    // params.set("username", userNmae);
    return axios.post(HOST_DOMAIN + "/login", {
      account: userNmae,
      passward: password
    });
  }

  static register(user: UserInfo) {
    return axios.post(HOST_DOMAIN + "/register", user);
  }

  static getUserList() {
    return axios.get(HOST_DOMAIN + "/system/role/teacherandstudent");
  }

  static getClass() {
    return axios.post(HOST_DOMAIN + "/system/role/teacherandstudent");
  }
}
