<template>
  <div class="masks">
    <div class="gesturePwd">
      <div style="position: absolute;top:0;left:0;right:0;bottom:0;">
        <!-- 列表组顶部说明文字 -->
        <div class="weui-cells__title">签到历史记录</div>
        <div class="weui-cells">
          <div class="weui-cell">
            <div class="weui-cell__bd">
              <p>签到id</p>
            </div>
            <div class="weui-cell__ft">发起签到时间</div>
          </div>
          <div
            class="weui-cell"
            v-for="signInfo in signList"
            v-bind:key="signInfo.teacherSignId"
          >
            <div class="weui-cell__bd">
              <p>{{ signInfo.teacherSignId }}</p>
            </div>
            <div class="weui-cell__ft">
              {{ signInfo.startTime + "~" + signInfo.stopTime.substr(11, 24) }}
            </div>
          </div>
        </div>
        <div class="button-sp-area">
          <a
            href="javascript:"
            class="weui-btn weui-btn_mini weui-btn_primary"
            @click="goBack"
            >返回</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Getter } from "vuex-class";
import API, { KResponse } from "@/utils/api";
import {
  ToastOptions,
  SuccessToastOptions
} from "../components/base/toast/index";

@Component
export default class Sign extends Vue {
  private signList = [];
  @Getter("User/getUserInfo") userInfo: any;

  constructor() {
    super();
  }
  mounted() {
    API.getSignInList(this.userInfo.userId)
      .then(data => {
        console.log(data.data.rows);
        this.signList = data.data.rows;
      })
      .catch((res: KResponse) => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  goBack() {
    // console.log(this.signState.password);
    this.$router.go(-1);
  }
}
</script>
<style lang="less">
.button-sp-area {
  margin: 15px auto;
  text-align: center;
}

.button-sp-area .weui-btn_mini {
  margin-left: 10px;
  width: 100px;
}
</style>
