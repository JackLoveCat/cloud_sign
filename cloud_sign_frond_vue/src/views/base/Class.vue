<template>
  <div class="page__bd">
    <div class="weui-tab">
      <div class="weui-navbar">
        <div
          class="weui-navbar__item weui-bar__item_on"
          v-show="this.userInfo.roleid === 2"
        >
          我创建的班课
        </div>
        <div
          v-show="this.userInfo.roleid === 3"
          class="weui-navbar__item weui-bar__item_on"
        >
          我加入的班课
        </div>
      </div>
      <div class="weui-tab__panel">
        <Search @search="search" @reset="reset"></Search>
        <div class="weui-panel weui-panel_access">
          <div class="weui-panel__bd">
            <div
              class="weui-form-preview"
              v-for="cla in classes"
              v-bind:key="cla.courseId"
            >
              <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                  <label class="weui-form-preview__label">课程名称</label>
                  <em class="weui-form-preview__value">{{ cla.courseName }}</em>
                </div>
              </div>
              <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                  <label class="weui-form-preview__label">班级名称</label>
                  <span class="weui-form-preview__value">{{
                    cla.className
                  }}</span>
                </div>
                <div
                  class="weui-form-preview__item"
                  v-clipboard:copy="cla.courseNum"
                  v-clipboard:success="copySuccess"
                  v-clipboard:error="copyError"
                >
                  <label class="weui-form-preview__label">班课编号</label>
                  <span class="weui-form-preview__value">{{
                    cla.courseNum
                  }}</span>
                </div>
                <div class="weui-form-preview__item">
                  <label class="weui-form-preview__label">班课说明</label>
                  <span class="weui-form-preview__value">{{ cla.remark }}</span>
                </div>
              </div>
              <div class="weui-form-preview__ft">
                <a
                  class="weui-form-preview__btn weui-form-preview__btn_primary"
                  href="javascript:"
                  @click="goSign(cla.courseId)"
                >
                  {{ userInfo.roleid === 2 ? "发起签到" : "签到" }}</a
                ><a
                  class="weui-form-preview__btn weui-form-preview__btn_primary"
                  href="javascript:"
                  @click="goMyclass(cla.courseId)"
                  >查看详情</a
                >
              </div>
            </div>
          </div>
          <a
            href="javascript:"
            class="weui-cell weui-cell_active weui-cell_link"
            @click="createOrJoin"
          >
            <div class="weui-cell__bd">
              {{ userInfo.roleid === 2 ? "创建班课" : "加入班课" }}
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Search from "@/components/base/Search.vue";
import { CourseInfo } from "@/types/model/Class";
import Api, { KResponse } from "@/utils/api";
import { Getter } from "vuex-class";
import {
  ToastOptions,
  SuccessToastOptions
} from "../../components/base/toast/index";

@Component({
  components: {
    Search
  }
})
export default class Class extends Vue {
  @Getter("User/getUserInfo") userInfo: any;
  private classes: Array<CourseInfo> = [];
  private classesBack: Array<CourseInfo> = [];
  constructor() {
    super();
  }
  mounted() {
    this.loadData();
  }
  setClass(classes: any) {
    this.classes = classes;
  }
  copySuccess() {
    this.$toptips.show(new SuccessToastOptions("班课号拷贝成功"));
  }
  copyError() {
    // console.log("copy failed");
  }
  loadData() {
    if (this.userInfo.roleid === 3) {
      Api.getMyJoin()
        .then((res: KResponse) => {
          this.classes = res.data.rows;
        })
        .catch((res: KResponse) => {
          // console.log(res);
          this.$toptips.show(new ToastOptions(res.msg));
        });
    } else {
      Api.getMyCreate()
        .then((res: KResponse) => {
          this.setClass(res.data.rows);
        })
        .catch((res: KResponse) => {
          // console.log(res);
          this.$toptips.show(new ToastOptions(res.msg));
        });
    }
  }

  goMyclass(classId: number) {
    this.$router.push({
      name: "MyClass",
      query: { myClassId: "" + classId }
    });
  }
  goSign(courseId: number) {
    //TODO 查询签到 如果当前有则开始
    this.$router.push({
      name: "Sign",
      query: { myClassId: "" + courseId }
    });
  }
  createOrJoin() {
    if (this.userInfo.roleid === 2) {
      this.$router.push({ name: "CourseCreate" });
    } else {
      this.$router.push({ name: "CourseJoin" });
    }
  }
  search(text: string) {
    // console.log("parent search:" + text);
    // TODO: 搜索课程
    if (
      typeof this.classesBack === "undefined" ||
      this.classesBack.length <= 0
    ) {
      this.classesBack = this.classes;
    }
    this.classes = this.classesBack.filter(
      e =>
        (e.courseName && e.courseName.indexOf(text) > -1) ||
        (e.className && e.className.indexOf(text) > -1) ||
        (e.courseNum && e.courseNum.indexOf(text) > -1)
    );
  }
  reset() {
    if (
      !(typeof this.classesBack === "undefined" || this.classesBack.length <= 0)
    ) {
      this.classes = this.classesBack;
    }
  }
}
</script>
