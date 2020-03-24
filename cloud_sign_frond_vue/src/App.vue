<template>
  <div id="app" class="container">
    <router-view />
    <Footer v-if="$route.meta.showFoot"></Footer>
  </div>
</template>
<script>
// @ is an alias to /src
import Footer from "@/components/page/Footer.vue";

export default {
  name: "App",
  components: {
    Footer
  }
};
</script>

<style lang="less">
@import url("less/base/variable/color.less");
body {
  background: @weuiBgColorActive;
}
.container {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;
  color: var(--weui-FG-0);
}
</style>
