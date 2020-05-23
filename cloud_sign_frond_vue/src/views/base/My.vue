<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-24 21:39:10
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-05-22 23:19:29
 -->
<template>
  <div class="page__bd">
    <div class="weui-cells">
      <div class="weui-cell weui-cell_example">
        <div class="weui-cell__hd">
          <img
            src="../../assets/imgs/icon/user_icon_default.jpg"
            alt=""
            style="width: 20px; margin-right: 16px; display: block;"
          />
        </div>
        <div class="weui-cell__bd">
          <p>{{ user.userName }}</p>
        </div>
        <div class="weui-cell__ft">{{ user.nickName }}</div>
      </div>
      <div class="weui-cell weui-cell_example">
        <div class="weui-cell__bd">
          <p>邮箱</p>
        </div>
        <div class="weui-cell__ft">{{ user.email }}</div>
      </div>
      <div class="weui-cell weui-cell_example">
        <div class="weui-cell__bd">
          <p>手机号</p>
        </div>
        <div class="weui-cell__ft">{{ user.phonenumber }}</div>
      </div>
      <div class="weui-cell weui-cell_example">
        <div class="weui-cell__bd">
          <p>性别</p>
        </div>
        <div class="weui-cell__ft">{{ user.avatar }}</div>
      </div>
      <div class="weui-cell weui-cell_example">
        <div class="weui-cell__bd">
          <p>角色</p>
        </div>
        <div class="weui-cell__ft">{{ user.roleName }}</div>
      </div>
    </div>

    <div class="weui-cells">
      <a
        class="weui-cell weui-cell_access"
        href="javascript:"
        @click="goMyMedal"
      >
        <div class="weui-cell__bd">
          <p>我的勋章</p>
        </div>
        <div class="weui-cell__ft">敬请期待</div>
      </a>
      <a class="weui-cell weui-cell_access" @click="goPage('About')">
        <div class="weui-cell__bd">
          <p>隐私政策</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
      <a class="weui-cell weui-cell_access" @click="changePassword">
        <div class="weui-cell__bd">
          <p>修改密码</p>
        </div>
        <div class="weui-cell__ft"></div>
      </a>
      <a class="weui-cell weui-cell_access" @click="logout">
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
import { Action, State, Getter } from "vuex-class";
import Api from "@/utils/api";
import { SuccessToastOptions } from "../../components/base/toast/index";

@Component
export default class My extends Vue {
  @Action("User/logout") logoutAction!: Function;
  @Getter("User/getUserInfo") user: any;
  constructor() {
    super();
  }
  goPage(page: string) {
    this.$router.push({ name: "PrivatePolicy" });
  }
  goMyMedal() {
    this.$toptips.show(new SuccessToastOptions("勋章功能开发中,敬请期待"));
  }
  logout() {
    this.logoutAction();
    this.$router.push({ name: "Login" });
  }
  changePassword() {
    this.$toptips.show(new SuccessToastOptions("修改功能开发中,敬请期待"));
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
