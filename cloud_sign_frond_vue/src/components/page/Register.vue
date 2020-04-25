<template>
  <div class="page">
    <div class="page__bd page__bd_spacing">
      <div class="weui-form">
        <div class="weui-form__text-area">
          <h2 class="weui-form__title">注册</h2>
        </div>
        <div class="weui-form__control-area">
          <div class="weui-cells__group weui-cells__group_form">
            <div class="weui-cells weui-cells_form">
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">昵称</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="nickName"
                    v-model.trim="params.nickName"
                    class="weui-input"
                    placeholder="请输入昵称"
                  />
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">账号</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="userName"
                    v-model.trim="params.userName"
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
                    id="phonenumber"
                    v-model="params.phonenumber"
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

              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">性别</label>
                </div>
                <div class="weui-cell__bd">
                  <select
                    class="weui-select"
                    id="avatar"
                    v-model="params.avatar"
                  >
                    <option value="男">男</option>
                    <option value="女">女</option>
                  </select>
                </div>
              </div>
              <div class="weui-cell weui-cell_active weui-cell_switch">
                <div class="weui-cell__bd">
                  {{ params.roleIds[0] == 2 ? "老师" : "学生" }}
                </div>
                <div class="weui-cell__ft">
                  <label for="switchCP" class="weui-switch-cp">
                    <input
                      id="switchCP"
                      class="weui-switch-cp__input"
                      type="checkbox"
                      checked="checked"
                      @change="changeRole"
                    />
                    <div class="weui-switch-cp__box"></div>
                  </label>
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
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Action } from "vuex-class";
import { UserInfo } from "@/types/model/User";
import API, { KResponse } from "@/utils/api";
import { ToastOptions } from "../base/toast/index";

@Component
export default class Register extends Vue {
  @Action("User/login") saveLogin!: Function;
  private params: UserInfo = {
    avatar: "男",
    nickName: "",
    userName: "",
    password: "",
    repassword: "",
    email: "",
    phonenumber: "",
    roleIds: [2]
  };
  constructor() {
    super();
    // API.getUserList()
    //   .then(res => {
    //     console.log(res);
    //   })
    //   .catch(res => {
    //     console.log(res);
    //   });
  }
  register() {
    API.register(this.params)
      .then(res => {
        API.login(this.params.phonenumber, this.params.password)
          .then((res: KResponse) => {
            this.saveLogin(res.data.token);
            this.$router.push({ name: "Home" });
          })
          .catch((res: KResponse) => {
            this.$toptips.show(new ToastOptions(res.msg));
          });
      })
      .catch(res => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  goLogin() {
    this.$router.push({ name: "Login" });
  }
  changeRole() {
    if (this.params.roleIds[0] == 2) this.params.roleIds = [3];
    else this.params.roleIds = [2];
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import url("../../less/base/variable/global.less");

.page {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  box-sizing: border-box;
}
</style>
