<template>
  <div id="register">
    <div class="weui-form">
      <div class="weui-form__text-area">
        <h2 class="weui-form__title">注册</h2>
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
                  v-model.trim="params.account"
                  class="weui-input"
                  placeholder="请输入账号"
                />
              </div>
            </div>
            <div class="weui-cell weui-cell_active">
              <div class="weui-cell__hd">
                <label class="weui-label">手机号</label>
              </div>
              <div class="weui-cell__bd">
                <input
                  id="phone"
                  v-model="params.phone"
                  type="number"
                  pattern="[0-9]*"
                  class="weui-input"
                  placeholder="请填写绑定手机号"
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
                  placeholder="请输入密码"
                />
              </div>
            </div>
            <div class="weui-cell weui-cell_active">
              <div class="weui-cell__hd">
                <label class="weui-label">确认密码</label>
              </div>
              <div class="weui-cell__bd">
                <input
                  id="repassword"
                  type="password"
                  v-model="params.repassword"
                  class="weui-input"
                  placeholder="请再次输入密码"
                />
              </div>
            </div>
            <div class="weui-cell weui-cell_active">
              <div class="weui-cell__hd">
                <label class="weui-label">邮箱</label>
              </div>
              <div class="weui-cell__bd">
                <input
                  id="email"
                  v-model="params.email"
                  class="weui-input"
                  placeholder="填写绑定的邮箱"
                  type="email"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="weui-form__opr-area">
        <div class="weui-flex">
          <div class="weui-flex__item">
            <a @click="register" class="weui-btn weui-btn_primary">注册</a>
          </div>
          <div class="weui-flex__item">
            <a @click="goLogin" class="weui-btn weui-btn_primary">前往登录</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { RegisteParam } from "@/types/model/User";

@Component
export default class Register extends Vue {
  private params: RegisteParam = {
    account: "",
    password: "",
    repassword: "",
    email: "",
    phone: ""
  };
  register() {
    alert("register success:" + JSON.stringify(this.params));
  }
  goLogin() {
    this.$router.push({ name: "Login" });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import url("../../less/base/variable/global.less");

#register {
  width: 100vw;
  height: 300px;
  margin: 0 auto;
  position: relative;
  top: .reHeight(40px) [ @height]; /*偏移*/
}
</style>
