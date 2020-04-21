<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-25 01:07:54
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-06 19:52:01
 -->
<template>
  <div class="page__bd">
    <div class="weui-cells">
      <div class="weui-cell weui-cell_active">
        <div
          class="weui-cell__hd"
          style="position: relative; margin-right: 10px;"
        >
          <img src="images/pic_160.png" style="width: 50px; display: block;" />
        </div>
        <div class="weui-cell__bd">
          <p>神经计算网络及应用</p>
          <p style="font-size: 13px; color: #888;">神经计算网络及应用</p>
        </div>
      </div>
    </div>
    <div class="weui-cells">
      <div class="weui-cell weui-cell_active weui-cell_access" @click="goSign">
        <div class="weui-cell__bd">
          <span style="vertical-align: middle;">签到</span>
        </div>
        <div class="weui-cell__ft"></div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class MyClass extends Vue {
  constructor() {
    super();
  }
  goSign() {
    this.$router.push({ name: "Sign" });
  }
}
</script>
