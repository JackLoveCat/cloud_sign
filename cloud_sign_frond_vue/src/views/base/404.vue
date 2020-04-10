<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 21:53:19
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 22:44:41
 -->
<template>
  <div class="page_404">
    <div class="weui-msg">
      <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">404</h2>
        <p class="weui-msg__desc">页面不存在</p>
      </div>
      <div class="weui-msg__opr-area">
        <p class="weui-btn-area">
          <a href="/" class="weui-btn weui-btn_primary">前往首页</a>
        </p>
      </div>
    </div>
    <Footer></Footer>
    <Footer></Footer>
  </div>
</template>
<script lang="ts">
import Footer from "../../components/page/Footer.vue";
import Vue from "vue";
export default Vue.extend({
  name: "notFound",
  components: { Footer }
});
</script>
<style lang="less" scoped>
.page_404 {
  height: 100vh;
  background-image: url(../../assets/imgs/404_bg.jpeg);
}
.weui-msg {
  background-color: transparent;
}
</style>
