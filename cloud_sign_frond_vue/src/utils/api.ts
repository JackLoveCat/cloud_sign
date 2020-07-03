/*
{ AxiosInstance, AxiosRequestConfig, AxiosResponse }
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-22 20:23:12
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-03 23:47:55
 */
import axios from "axios";
import Vue from "vue";
import { UserInfo } from "@/types/model/User";
import { CourseInfo } from "@/types/model/Class";
import { getToken, getUserId } from "../store/model/User";
const HOST_DOMAIN = "http://123.206.49.117:8080";
export interface KResponse {
  ret: number;
  msg: string;
  data?: any;
}
class Response implements KResponse {
  ret = -1;
  msg = "";
  data?: any;
  constructor(ret: number, msg: string, data?: any) {
    this.ret = ret;
    this.msg = msg;
    this.data = data;
  }
}
axios.interceptors.request.use((config) => {
  let xtoken = getToken(); //我的token 存储在 localStorage中
  // console.log(xtoken) 如果 xtoken 存在 就设置请求头
  if (xtoken) {
    xtoken = "Bearer " + xtoken;
    // console.log(xtoken)
    config.headers["Authorization"] = xtoken; //配置请求头中  Authorization 字段的值为拿到的token
  }
  return config;
});
export default class Api {
  static uuid() {
    return "xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx".replace(/[xy]/g, function(c) {
      const r = (Math.random() * 16) | 0;
      const v = c == "x" ? r : (r & 0x3) | 0x8;
      return v.toString(16);
    });
  }

  static loginByPhone(userNmae: string, password: string) {
    return axios.post(HOST_DOMAIN + "/loginbyphone", {
      phonenum: userNmae,
      password: password,
      uuid: this.uuid(),
    });
  }
  static toQueryString(obj?: Map<string, string>) {
    if (undefined == obj) {
      return "";
    }
    const str = new Array<string>();
    obj.forEach((value, key) => {
      str.push(encodeURIComponent(key) + "=" + encodeURIComponent(value));
    });
    return str.join("&");
  }
  static login(userNmae: string, password: string) {
    return this.post(HOST_DOMAIN + "/login", {
      account: userNmae,
      passward: password,
    });
  }
  static getLoginUserInfo() {
    return this.get(HOST_DOMAIN + "/getInfo");
  }
  static register(user: UserInfo): Promise<KResponse> {
    return this.post(HOST_DOMAIN + "/register", user);
  }

  static getUserList() {
    return this.get(HOST_DOMAIN + "/system/role/teacherandstudent");
  }

  static getMyCreate() {
    return this.get(HOST_DOMAIN + "/cla/course/listmycreate");
  }

  static getMyJoin() {
    return this.get(HOST_DOMAIN + "/cla/course/listmyjoin");
  }
  static createCourse(param: CourseInfo) {
    return this.post(HOST_DOMAIN + "/cla/course/create", param);
  }

  static listCourse(param: any) {
    return this.get(HOST_DOMAIN + "/cla/course/list", param);
  }
  static joinCourse(courseId: any) {
    return this.post(HOST_DOMAIN + "/cla/course/join", courseId);
  }
  static getCourseInfo(classId: string) {
    return this.get(HOST_DOMAIN + "cla/course/" + classId);
  }

  static listUni() {
    return this.get(HOST_DOMAIN + "/system/uniacada/listuni");
  }

  static listUniByName(uniName: string) {
    return this.get(HOST_DOMAIN + "/system/uniacada/listacabyuni/" + uniName);
  }

  static getCourseInfoBynum(coursenum: string) {
    return this.get(HOST_DOMAIN + "/cla/course/coursenum/" + coursenum);
  }
  static getSignInfo(courseId: string) {
    return this.get(
      HOST_DOMAIN + "/signin/sign/getSignInCourseInfo/" + courseId
    );
  }
  static studentSignIn(
    courseId: string,
    gesture: string,
    location: string | unknown | undefined,
    signType = 1
  ) {
    return this.post(HOST_DOMAIN + "/signin/sign/studentSignIn", {
      courseId: courseId,
      studentId: getUserId(),
      signType: signType,
      gesture: gesture,
      remark: location ? location : "",
      ipaddr: "127.0.0.1",
    });
  }
  static teacherSignIn(
    courseId: string,
    gesture: string,
    location: string | undefined | unknown,
    signType = 1
  ) {
    return this.post(HOST_DOMAIN + "/signin/sign/teacherSignIn", {
      courseId: courseId,
      teacherId: getUserId(),
      startTime: this.formatDate(new Date()),
      signType: signType,
      gesture: gesture,
      remark: location ? location : "",
      ipaddr: "127.0.0.1",
      stopTime: this.formatDate(new Date(new Date().getTime() + 3600000)),
    });
  }

  private static formatDate(date: Date) {
    const y = date.getFullYear();
    const m = date.getMonth() + 1;
    const d = date.getDate();
    const H = date.getHours();
    const mm = date.getMinutes();
    const s = date.getSeconds();
    const ms = m < 10 ? "0" + m : m.toString();
    const ds = d < 10 ? "0" + d : d.toString();
    const Hs = H < 10 ? "0" + H : H.toString();
    return y + "-" + ms + "-" + ds + " " + Hs + ":" + mm + ":" + s;
  }

  static teacherSignInStop(courseId: string) {
    return this.post(HOST_DOMAIN + "/signin/sign/teacherSignInStop", {
      courseId: courseId,
      teacherId: getUserId(),
    });
  }

  /**
   * 封装post请求，封装统一的loading
   * @param url
   * @param param
   */
  private static post(url: string, param?: any): Promise<KResponse> {
    Vue.prototype.$loading.show({ text: "加载中" });
    return new Promise(function(resolve, rejext) {
      axios
        .post(url, param)
        .finally(() => {
          Vue.prototype.$loading.hidden();
        })
        .then((res) => {
          if (res.data && res.data.code && res.data.code === 200)
            resolve(
              new Response(
                res.data.code,
                Api.getErrorMsg(res.data),
                res.data.data ? res.data.data : res.data
              )
            );
          else
            rejext(
              new Response(
                res.data.code,
                Api.getErrorMsg(res.data),
                res.data.data ? res.data.data : res.data
              )
            );
        })
        .catch((err) => {
          if (err.response && err.response.data) {
            rejext(
              new Response(
                err.response.data.status,
                Api.getErrorMsg(err.response.data),
                err.response.data.data
                  ? err.response.data.data
                  : err.response.data
              )
            );
          }
          rejext({ ret: -1, msg: "网络通讯异常" } as Response);
        });
    });
  }
  /**
   * 处理返回值错误消息
   * @param resp
   */
  private static getErrorMsg(resp: any): string {
    if (resp.msg) {
      return resp.msg;
    } else if (resp.message) {
      return resp.message;
    } else if (resp.errors && resp.errors[0] && resp.errors[0].defaultMessage) {
      return resp.errors[0].defaultMessage;
    }
    return "服务请求未知异常";
  }
  /**
   * get 请求参数
   * @param url
   * @param param
   */
  private static get(url: string, param?: any): Promise<KResponse> {
    Vue.prototype.$loading.show({ text: "加载中" });
    return new Promise(function(resolve, rejext) {
      axios
        .get(url, param)
        .finally(() => {
          Vue.prototype.$loading.hidden();
        })
        .then((res) => {
          if (res.data && res.data.code && res.data.code === 200)
            resolve(
              new Response(
                res.data.code,
                Api.getErrorMsg(res.data),
                res.data.data ? res.data.data : res.data
              )
            );
          else
            rejext(
              new Response(
                res.data.code,
                Api.getErrorMsg(res.data),
                res.data.data ? res.data.data : res.data
              )
            );
        })
        .catch((err) => {
          if (err.response.data) {
            rejext(
              new Response(
                err.response.data.status,
                Api.getErrorMsg(err.response.data),
                err.response.data.data
                  ? err.response.data.data
                  : err.response.data
              )
            );
          }
          rejext({ ret: -1, msg: "网络通讯异常" } as Response);
        });
    });
  }
}
