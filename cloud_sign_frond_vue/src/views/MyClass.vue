<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-25 01:07:54
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-04 18:30:55
 -->
<template>
  <div class="page__bd">
    <div class="weui-cells">
      <div class="weui-cell weui-cell_active">
        <div
          class="weui-cell__hd"
          style="position: relative; margin-right: 10px;"
        >
          <img
            src="../assets/imgs/icon/class.png"
            style="width: 50px; display: block;"
          />
        </div>
        <div class="weui-cell__bd">
          <p>{{ classInfo.className }}</p>
          <p style="font-size: 13px; color: #888;">
            {{ classInfo.courseName }}
          </p>
        </div>
      </div>
      <div class="weui-cell  weui-cell_example">
        <div class="weui-cell__bd">
          <p>班课号</p>
        </div>
        <div class="weui-cell__ft">{{ classInfo.courseNum }}</div>
      </div>
      <div class="weui-cell  weui-cell_example">
        <div class="weui-cell__bd">
          <p>学期</p>
        </div>
        <div class="weui-cell__ft">{{ classInfo.semester }}</div>
      </div>
      <div class="weui-cell  weui-cell_example">
        <div class="weui-cell__bd">
          <p>考试说明</p>
        </div>
        <div class="weui-cell__ft">{{ classInfo.examArrangement }}</div>
      </div>
      <div class="weui-cell  weui-cell_example">
        <div class="weui-cell__bd">
          <p>创建时间</p>
        </div>
        <div class="weui-cell__ft">{{ classInfo.createTime }}</div>
      </div>
    </div>
    <div class="weui-cells">
      <div class="weui-cell weui-cell_active weui-cell_access" @click="goSign">
        <div class="weui-cell__bd">
          <span style="vertical-align: middle;">{{
            userInfo.roleid === 2 ? "发起签到" : "签到"
          }}</span>
        </div>
        <div class="weui-cell__ft"></div>
      </div>
      <div
        class="weui-cell weui-cell_active weui-cell_access"
        v-show="userInfo.roleid === 2"
        @click="showSignList"
      >
        <div class="weui-cell__bd">
          <span style="vertical-align: middle;">发起签到记录</span>
        </div>
        <div class="weui-cell__ft"></div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Api, { KResponse } from "@/utils/api";
import { ToastOptions } from "../components/base/toast/index";
import { Getter } from "vuex-class";

@Component
export default class MyClass extends Vue {
  @Getter("User/getUserInfo") userInfo: any;
  private classInfo = { courseName: "" };
  constructor() {
    super();
  }
  mounted() {
    if (!this.$route.query.myClassId) {
      this.$toptips.show(new ToastOptions("未知的班课"));
    }
    Api.getCourseInfo(this.$route.query.myClassId as string)
      .then((res: KResponse) => {
        this.classInfo = res.data;
      })
      .catch((res: KResponse) => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  goSign() {
    this.$router.push({
      name: "Sign",
      query: { myClassId: this.$route.query.myClassId }
    });
  }
  showSignList() {
    this.$router.push({
      name: "SignList"
    });
  }
}
</script>
