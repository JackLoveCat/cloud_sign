<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-12 00:27:37
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-13 00:57:39
 -->
<template>
  <div :class="customClass">
    <div class="weui-mask"></div>
    <div :class="{ 'weui-dialog':true, 'weui-skin_android':isAndroid }">
      <div class="weui-dialog__hd" v-if="title">
        <strong class="weui-dialog__title">{{title}}</strong>
      </div>
      <div class="weui-dialog__bd">{{content}}</div>
      <div class="weui-dialog__ft" v-for="{index,button} in buttons" :key="index">
        <a
          href="javascript:;"
          :class="`weui-dialog__btn weui-dialog__btn_${button.type}`"
        >{{ button.label }}</a>
      </div>
    </div>
  </div>
</template>
<script  lang='ts'>
import { Component, Prop, Vue } from "vue-property-decorator";
import { BtnOption } from "./option.d";
@Component
export default class KmDialog extends Vue {
  @Prop() private title!: string;
  @Prop() private content!: string;
  @Prop({ type: String, default: "" }) private customClass?: string;
  @Prop() private buttons?: Array<BtnOption>;
  constructor() {
    super();
  }
  isAndroid() {
    return false;
  }
}
</script>