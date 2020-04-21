/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-22 20:34:50
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-05 21:36:43
 */
// 登录
export interface LoginParams {
  account: string;
  password: string;
}

export interface UserInfo {
  userId?: Number;
  avatar: string;
  email: string;
  nickName: string;
  password: string;
  repassword: string;
  phonenumber: string;
  roleIds: Array<Number>;
  uniacadaId?: Number;
  userName: string;
}
