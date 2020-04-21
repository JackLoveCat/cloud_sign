<template>
  <div
    class="weui-toptips weui-toptips_warn"
    v-show="isShow"
    style="display: block;"
  >
    {{ msg }}
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { State } from "vuex-class";
import { ToastOptionInterface } from "./index";
@Component
export default class TopTips extends Vue {
  private isShow = false;
  private msg = "";
  private interval = -1;
  public show(options: ToastOptionInterface) {
    this.isShow = true;
    this.msg = options.text;
    if (this.interval === -1) {
      this.interval = setInterval(() => {
        this.isShow = false;
      }, 3000);
    }
  }

  public hidden() {
    this.isShow = false;
    this.interval = -1;
  }
}
</script>
