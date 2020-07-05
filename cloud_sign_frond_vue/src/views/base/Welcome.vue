<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-06 17:42:13
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-05 17:40:51
 -->
<template>
  <div class="welcome">
    <div class="logo" v-show="type === 1">
      <div class="logo_center">
        <img src="../../assets/imgs/welcome.svg" style="display:block;" />
        <h1>到云 1.0</h1>
      </div>
    </div>
    <div v-show="type === 2" class="logo_full">
      <img src="../../assets/imgs/welcome_bg.svg" />
      <h1 id="first_title">轻 松 学 习</h1>
      <h1 id="second_title">“ 到 云 ” 一 游</h1>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { ToastOptions } from "../../components/base/toast/index";

@Component
export default class Welcome extends Vue {
  private type = 1;
  private timer?: number;
  mounted() {
    if (!localStorage.getItem("welcome")) {
      this.type = 1;
    } else {
      this.type = 2;
    }
    localStorage.setItem(
      "welcome",
      JSON.stringify({ t: new Date().getTime() + 3600 * 24 })
    );
    this.timer = setInterval(() => {
      if (this.type === 1) this.type = 2;
      else {
        clearInterval(this.timer);
        this.$router.push({ name: "Home" });
      }
    }, 3000);
  }
}
</script>
<style scoped>
.welcome {
  width: 100vw;
  height: 100vh;
}
.logo {
  width: 100vw;
  height: 100vh;
  position: relative;
  overflow: hidden;
  float: left;
  left: 24%;
  top: 37%;
}
.logo .logo_center {
  width: 200px;
}
.logo img {
  float: left;
}
.logo h1 {
  float: left;
  margin-left: 8px;
}
.logo_full img {
  position: absolute;
  top: 50%;
  left: 50%;
  display: block;
  min-width: 100%;
  min-height: 100%;
  transform: translate(-50%, -50%);
}
.logo_full h1 {
  position: absolute;
  width: 211px;
  height: 66px;
  padding: 3px 2px 3px 2px;
  font-family: "Matura MT Script Capitals";
  font-weight: 700;
  font-style: normal;
  letter-spacing: normal;
  color: #ffffff;
  vertical-align: none;
  text-align: center;
  text-transform: none;
  background-color: transparent;
  border-color: transparent;
}
.logo_full #first_title {
  left: 50px;
  top: 226px;
}
.logo_full #second_title {
  right: 50px;
  top: 300px;
}
</style>
