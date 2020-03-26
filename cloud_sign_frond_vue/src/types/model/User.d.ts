/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-22 20:34:50
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-22 22:06:29
 */
// 登录
export interface LoginParams {
  account: string;
  password: string;
}

export interface RegisteParam {
  account: string;
  password: string;
  repassword: string;
  phone: string;
  email: string;
}
export interface UserInfo {
  _id: string;
  name: string;
  avatar: string | any;
}
