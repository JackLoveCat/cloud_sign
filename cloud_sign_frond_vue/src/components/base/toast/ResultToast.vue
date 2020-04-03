<template>
  <div id="toast" :style="{ opacity: opacity, display: none }">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
      <i class="weui-icon-success-no-circle weui-icon_toast"></i>
      <p class="weui-toast__content">{{ msg }}</p>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
@Component
export default class ResultToast extends Vue {
  @Prop() private msg: string;
  private opacity = 0;
  constructor() {
    super();
    this.msg = "";
  }
  public show() {
    this.opacity = 1;
  }
}
</script>
