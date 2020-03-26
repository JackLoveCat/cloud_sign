<template>
  <div class="page form_page" id="login">
    <div class="weui-form">
      <div class="weui-form__text-area">
        <h2 class="weui-form__title">登录</h2>
      </div>
      <div class="weui-form__control-area">
        <div class="weui-cells__group weui-cells__group_form">
          <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_active">
              <div class="weui-cell__hd">
                <label class="weui-label">账号</label>
              </div>
              <div class="weui-cell__bd">
                <input
                  id="account"
                  v-model="params.account"
                  class="weui-input"
                  placeholder="支持手机号/邮箱/用户名登录"
                />
              </div>
            </div>
            <div class="weui-cell weui-cell_active">
              <div class="weui-cell__hd">
                <label class="weui-label">密码</label>
              </div>
              <div class="weui-cell__bd">
                <input
                  id="password"
                  v-model="params.password"
                  type="password"
                  class="weui-input"
                  placeholder="请输入账号密码"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="weui-form__opr-area">
        <div class="weui-flex">
          <div class="weui-flex__item">
            <a @click="login" class="weui-btn weui-btn_primary">登录</a>
          </div>
          <div class="weui-flex__item">
            <a @click="goRegister" class="weui-btn weui-btn_primary"
              >前往注册</a
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { LoginParams } from "@/types/model/User";
import state from "@/store/index";
@Component
export default class Login extends Vue {
  private params: LoginParams = {
    account: "",
    password: ""
  };
  login() {
    state.commit("login");
    console.log("login success:" + JSON.stringify(this.params));
    this.$router.push({ name: "Home" });
  }
  goRegister() {
    this.$router.push({ name: "Register" });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import url("../../less/base/variable/global.less");

#login {
  width: 100vw;
  height: 300px;
  margin: 0 auto;
  position: relative;
  top: .reHeight(60px) [ @height]; /*偏移*/
}
</style>
