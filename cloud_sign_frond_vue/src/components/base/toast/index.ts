/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-04 19:40:44
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-05 22:02:58
 */
import Loading from "./Loading.vue";
import TopTips from "./TopTips.vue";
import { VueConstructor } from "vue/types/vue";

export interface ToastOptionInterface {
  text: string;
}
export interface ToastInterface {
  show(options: ToastOptionInterface): void;
  hidden(): void;
}
export class ToastOptions implements ToastOptionInterface {
  text = "";
  constructor(text: string) {
    this.text = text;
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
