<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 08:54:55
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-03 22:38:00
 -->
<template>
  <div id="toast" style="opacity: 0; display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
      <i class="weui-icon-success-no-circle weui-icon_toast"></i>
      <p class="weui-toast__content">{{ msg }}</p>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { State } from "vuex-class";
@Component
export default class Loading extends Vue {
  @Prop() private msg: string;
  constructor() {
    super();
    this.msg = "";
  }
}
</script>
