/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 16:45:18
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 17:38:19
 */
import { DirectiveOptions } from "vue";
import { DirectiveBinding } from "vue/types/options";

/**
 * 校验实现
 * @param el
 * @param binding
 */
function validInput(el: HTMLInputElement, binding: DirectiveBinding) {
  el.onkeyup = () => {
    console.log("");
  };
  el.onclick = () => {
    let data = el.value;
    let rules = binding.value;
    if (rules instanceof Object) {
      if (rules.minLength !== undefined && data.length < rules.minLength) {
      }
    }
  };
}

/**
 * 按钮类型事件绑定分发
 * @param el
 * @param binding
 */
function validateValue(el: HTMLElement, binding: DirectiveBinding) {
  if (el instanceof HTMLInputElement) {
    validInput(el as HTMLInputElement, binding);
  } else {
    throw new Error(`not support elemnt ${el.tagName} validate`);
  }
}

/**
 * 短信倒计时指令
 */
export const validate: DirectiveOptions = {
  bind(el, binding) {
    validateValue(el, binding);
  },
  update(el, binding) {},
  unbind(el, binding) {}
};
