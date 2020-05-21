<template>
  <div class="page__bd page__bd_spacing">
    <div class="weui-footer weui-footer_fixed-bottom">
      <p class="weui-footer__links">
        <a @click="goHome" class="weui-footer__link">到云项目团队</a>
      </p>
      <p class="weui-footer__text">
        Copyright &copy; 2020-2027 www.cloud_sign.com
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Footer extends Vue {
  goHome() {
    this.$router.push({ name: "Home" }).catch((res) => {
      console.debug("page go failed:" + res);
    });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less"></style>
