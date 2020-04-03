<template>
  <div class="site-wrapper site-page--login">
    <div class="site-content__wrapper">
      <div class="site-content">
        <div class="brand-info">
          <img src="~@/assets/img/logo.png" alt="">
          <h2 class="brand-info__text" style="display: inline-block">到 云</h2>
          <p class="brand-info__intro">到云——第27组</p>

        </div>
        <div class="login-main" v-if="isForgetPwd">
          <h3 class="login-title">登录系统</h3>
          <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
            <el-form-item prop="userName">
              <el-input v-model="dataForm.userName" placeholder="账号/工号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item prop="captcha">
              <el-row :gutter="20">
                <el-col :span="14">
                  <el-input v-model="dataForm.captcha" placeholder="验证码">
                  </el-input>
                </el-col>
                <el-col :span="10" class="login-captcha">
                  <img :src="captchaPath" @click="updataPath()" alt="">
                </el-col>
              </el-row>
              <a @click="isForgetPwd=!isForgetPwd">忘记密码</a>
            </el-form-item>
            <el-form-item>
              <el-checkbox v-model="dataForm.remPsd">自动登陆</el-checkbox>
              <el-button class="login-btn-submit" type="primary" @click="dataFormSubmit()">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="login-main" v-else="isForgetPwd">
          <h3 class="login-title">忘记密码</h3>
          <el-form>
            <el-form-item>
              <el-input v-model="isForgetPwdUserName" placeholder="输入待找回的学号或工号,密码发送至绑定邮箱"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="button" :class="{disabled: !this.canClick}" @click="countDown" :disabled="!canClick">{{content}}</el-button>
              <el-button type="success" @click="isForgetPwd=!isForgetPwd">立即登陆</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        dataForm: {
          userName: '',
          password: '',
          // uuid: '',
          captcha: '',
          role:'1',
          remPsd:true,
        },
        isForgetPwd:true,
        isForgetPwdUserName:'',
        content:'发送验证码',
        canClick:true,
        totalTime: 60,      //记录具体倒计时时间
        dataRule: {
          userName: [
            { required: true, message: '账号/工号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          captcha: [
            { required: true, message: '验证码不能为空', trigger: 'blur' }
          ]
        },
        captchaPath: '',
      }
    },
    created () {
      this.getCaptcha()
    },
    methods: {
      // 提交表单
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl('/role/login.do'),
              method: 'post',
              data: this.$http.adornData({
                'username': this.dataForm.userName,
                'password': this.dataForm.password,
                'captcha': this.dataForm.captcha,
                'role': this.dataForm.role,
                'rememberMe': this.dataForm.remPsd
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.$cookie.set('token', data.token, data.timeOut)
                this.$router.replace({name: 'home'})
                console.log(data.status)
              } else {
                this.getCaptcha()
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      // 获取验证码
      getCaptcha() {
        this.captchaPath = this.$http.adornUrl(`/role/checkCode`)
      },
      updataPath() {
        this.captchaPath += '?'
      },
      countDown() {
        this.getPwd()
        if (!this.canClick) return
        this.canClick = false
        this.content = this.totalTime + 's后重新发送'
        let clock = window.setInterval(() => {
          this.totalTime--
          this.content = this.totalTime + 's后重新发送'
          if (this.totalTime < 0) {
            window.clearInterval(clock)
            this.content = '重新发送验证码'
            this.totalTime = 60
            this.canClick = true  //这里重新开启
          }
        }, 1000)
      },

      getPwd() {
        if (this.isForgetPwdUserName == null || this.isForgetPwdUserName == "") {
          this.$message.error("账号或工号不能为空")
          return false;
        } else {
          this.$http({
            url: this.$http.adornUrl('/role/forgetpassword.do'),
            method: 'post',
            data: this.$http.adornData({
              'userName': this.isForgetPwdUserName
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$message.success("发送成功，请前往绑定邮箱查看")
              return true
            } else {
              this.$message.error(data.msg)
              return false
            }
          })
        }

      }
    }
  }
</script>

<style lang="scss">
  .site-wrapper.site-page--login {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: rgba(38, 50, 56, .6);
    overflow: hidden;
    &:before {
      position: fixed;
      top: 0;
      left: 0;
      z-index: -1;
      width: 100%;
      height: 100%;
      content: "";
      background-image: url(~@/assets/img/login_bg.jpg);
      background-size: cover;
    }
    .site-content__wrapper {
      position: absolute;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      padding: 0;
      margin: 0;
      overflow-x: hidden;
      overflow-y: auto;
      background-color: transparent;
    }
    .site-content {
      min-height: 100%;
      padding: 30px 500px 30px 30px;
    }
    .brand-info {
      margin: 220px 100px 0 90px;
      color: #fff;
    }
    .brand-info__text {
      margin:  0 0 22px 0;
      font-size: 48px;
      font-weight: 400;
      text-transform : uppercase;
    }
    .brand-info__intro {
      margin: 10px 0;
      font-size: 16px;
      line-height: 1.58;
      opacity: .6;
    }
    .login-main {
      position: absolute;
      top: 0;
      right: 0;
      padding: 150px 60px 180px;
      width: 470px;
      min-height: 100%;
      background-color: #fff;
    }
    .login-title {
      font-size: 16px;
    }
    .login-captcha {
      overflow: hidden;
      > img {
        width: 100%;
        cursor: pointer;
      }
    }
    .login-btn-submit {
      width: 100%;
      margin-top: 38px;
    }
  }
  .disabled{
    background-color: #ddd;
    border-color: #ddd;
    color:#57a3f3;
    cursor: not-allowed; // 鼠标变化
  }
</style>
