<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-24 21:39:10
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-31 21:49:20
 -->
<template>
  <div class="page__bd">
    <div class="weui-cells">
      <div class="weui-cell  weui-cell_example">
        <div class="weui-cell__hd">
          <img
            src="../../assets/imgs/icon/icon_tabbar.png"
            alt=""
            style="width: 20px; margin-right: 16px; display: block;"
          />
        </div>
        <div class="weui-cell__bd">
          <p>叶斌</p>
        </div>
        <div class="weui-cell__ft">今天心情好</div>
      </div>
    </div>
    <div class="weui-cells">
      <div class="weui-flex">
        <div class="weui-flex__item"><div class="placeholder">28</div></div>
        <div class="weui-flex__item"><div class="placeholder">30</div></div>
        <div class="weui-flex__item"><div class="placeholder">27</div></div>
        <div class="weui-flex__item"><div class="placeholder">12</div></div>
      </div>
      <div class="weui-flex">
        <div class="weui-flex__item"><div class="placeButtom">经验</div></div>
        <div class="weui-flex__item"><div class="placeButtom">魅力</div></div>
        <div class="weui-flex__item"><div class="placeButtom">兰豆</div></div>
        <div class="weui-flex__item"><div class="placeButtom">心意</div></div>
      </div>
    </div>
    <div class="weui-cells">
      <a class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
          <p>我的勋章</p>
        </div>
        <div class="weui-cell__ft">已占用80M</div>
      </a>
      <a class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
          <p>文件暂存区</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
      <a class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
          <p>分享给朋友</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
      <a class="weui-cell  weui-cell_access" @click="goPage('About')">
        <div class="weui-cell__bd">
          <p>隐私政策</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
      <a class="weui-cell  weui-cell_access" @click="logout">
        <div class="weui-cell__bd">
          <p>登出</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import state from "@/store/index";

@Component
export default class My extends Vue {
  goPage(page: string) {
    this.$router.push({ name: page });
  }
  logout() {
    state.commit("logout");
    this.$router.push({ name: "Login" });
  }
}
</script>
<style scoped>
.placeholder {
  padding: 0 10px;
  background-color: var(--weui-BG-1);
  height: 2.3em;
  line-height: 2.3em;
  text-align: center;
  color: var(--weui-FG-1);
}
.placeButtom {
  padding: 0 10px;
  background-color: var(--weui-BG-1);
  height: 2.3em;
  line-height: 2.3em;
  text-align: center;
  color: var(--weui-FG-1);
}
</style>
