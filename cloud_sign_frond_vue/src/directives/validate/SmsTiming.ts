/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 16:45:18
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 17:31:22
 */
import { DirectiveOptions } from "vue";
import { DirectiveBinding } from "vue/types/options";

/**
 * 倒计时秒数
 */
const COUNT_DOWN_SEC = 60;

/**
 * 倒计时实现
 * @param el
 * @param binding
 */
const bindSmsTiming = (el: HTMLElement, binding: DirectiveBinding) => {
  let countDownSec = COUNT_DOWN_SEC;
  let interval = -1;
  let oldBtnText = el.innerText;
  let oldBackColor = el.style.backgroundColor;
  el.onclick = () => {
    if (interval != -1) {
      return;
    }
    let countDown = () => {
      //开始倒计时
      el.style.backgroundColor = "gray";
      el.innerHTML = countDownSec + " s";
      interval = setInterval(() => {
        countDownSec--;
        if (countDownSec > 0) {
          //变换按钮文字
          el.innerHTML = countDownSec + " s";
        } else {
          //结束倒计时
          el.innerHTML = oldBtnText;
          el.style.backgroundColor = oldBackColor;
          clearInterval(interval);
          interval = -1;
          countDownSec = COUNT_DOWN_SEC;
        }
      }, 1000);
    };
    //调用外部方法
    binding.value(() => {
      countDown();
    });
  };
};

/**
 * 短信倒计时指令
 */
export const SmsTiming: DirectiveOptions = {
  bind(el, binding) {
    bindSmsTiming(el, binding);
  },
  update(el, binding) {},
  unbind(el, binding) {}
};
