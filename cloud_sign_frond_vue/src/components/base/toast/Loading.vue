<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 08:54:55
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-05 21:50:47
 -->
<template>
  <div id="loadingToast" v-show="isShow" style="opacity:1">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
      <i class="weui-loading weui-icon_toast"></i>
      <p class="weui-toast__content">{{ msg }}</p>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { ToastInterface, ToastOptionInterface } from "./index";
import { State } from "vuex-class";
@Component
export default class Loading extends Vue implements ToastInterface {
  private isShow = false;
  private msg = "";
  public show(options: ToastOptionInterface) {
    this.isShow = true;
    this.msg = options.text;
  }

  public hidden() {
    this.isShow = false;
  }
}
</script>
