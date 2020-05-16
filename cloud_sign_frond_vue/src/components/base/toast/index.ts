/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-04 19:40:44
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-05-04 11:53:06
 */
import Loading from "./Loading.vue";
import TopTips from "./TopTips.vue";
import { VueConstructor } from "vue/types/vue";

export interface ToastOptionInterface {
  text: string;
  type: number;
}
export interface ToastInterface {
  show(options: ToastOptionInterface): void;
  hidden(): void;
}
export class ToastOptions implements ToastOptionInterface {
  text = "";
  type = 1;
  constructor(text: string) {
    if (text) this.text = text;
    else this.text = "未知异常";
  }
}
export class SuccessToastOptions implements ToastOptionInterface {
  text = "";
  type = 2;
  constructor(text: string) {
    if (text) this.text = text;
    else this.text = "未知异常";
  }
}
const instance = new Loading({ el: "#load" });
const tipIncance = new TopTips({ el: "#tips" });
const Toast = {
  install: function(vue: VueConstructor) {
    vue.prototype.$loading = instance;
    vue.prototype.$toptips = tipIncance;
  },
};
export default Toast;
