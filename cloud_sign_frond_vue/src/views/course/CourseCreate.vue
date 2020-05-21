<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-18 09:54:26
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-05-07 00:05:12
 -->
<template>
  <div class="page">
    <div class="page__bd">
      <div class="weui-form">
        <div class="weui-form__text-area">
          <h2 class="weui-form__title">创建班课</h2>
        </div>
        <div class="weui-form__control-area">
          <div class="weui-cells__group weui-cells__group_form">
            <div class="weui-cells weui-cells_form">
              <div
                class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after"
              >
                <div class="weui-cell__hd">
                  <label for="school" class="weui-label">学校</label>
                </div>
                <div class="weui-cell__bd">
                  <select
                    class="weui-select"
                    name="school"
                    v-model="chooseSchoole"
                    @change="loadschool"
                  >
                    <option
                      v-for="(school, index) in schooles"
                      :key="index"
                      :value="school"
                      >{{ school }}</option
                    >
                  </select>
                </div>
              </div>
              <div
                class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after"
                v-show="departments.length > 0"
              >
                <div class="weui-cell__hd">
                  <label for="department" class="weui-label">院系</label>
                </div>
                <div class="weui-cell__bd">
                  <select
                    class="weui-select"
                    name="department"
                    v-model="params.uniacadaId"
                  >
                    <option
                      v-for="department in departments"
                      :key="department.uniacadaId"
                      :value="department.uniacadaId"
                      >{{ department.academyName }}</option
                    >
                  </select>
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">班级名称</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="className"
                    v-model.trim="params.className"
                    class="weui-input"
                    placeholder="请输入班级名称"
                  />
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">课程名称</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="courseName"
                    v-model.trim="params.courseName"
                    class="weui-input"
                    placeholder="请输入课程名称"
                  />
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">学期</label>
                </div>
                <div class="weui-cell__bd">
                  <select
                    class="weui-select"
                    id="semester"
                    v-model="params.semester"
                  >
                    <option value="第一学期" selected="selected"
                      >第一学期</option
                    >
                    <option value="第二学期">第二学期</option>
                  </select>
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">考试安排</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="examArrangement"
                    v-model="params.examArrangement"
                    class="weui-input"
                    placeholder="请输入考试安排"
                  />
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">备注</label>
                </div>
                <div class="weui-cell__bd">
                  <textarea
                    class="weui-textarea"
                    v-model="params.remark"
                    id="remark"
                    placeholder="请输入课程描述"
                    rows="3"
                  ></textarea>
                </div>
              </div>
            </div>
          </div>
          <div class="button">
            <div class="button-sp-area">
              <a
                @click="createCourse"
                class="weui-btn weui-btn_mini weui-btn_primary"
              >
                创建</a
              >
              <a @click="cancel" class="weui-btn weui-btn_mini weui-btn_primary"
                >返回</a
              >
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
import { CourseInfo } from "@/types/model/Class";
import API, { KResponse } from "@/utils/api";
import { ToastOptions } from "../../components/base/toast/index";

@Component
export default class CourseCreate extends Vue {
  private params: CourseInfo = {
    className: "",
    courseName: "",
    coursePage: "",
    dataScope: "",
    courseNum: "",
    examArrangement: "",
    lectureProgress: "",
    remark: "",
    semester: "",
    studyRequirement: "",
    uniacadaId: -1,
  };
  private chooseSchoole = "";
  private schooles = [];
  private departments = [];

  mounted() {
    API.listUni()
      .then((res) => {
        this.schooles = res.data;
      })
      .catch((res) => {
        this.schooles = [];
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  isEmpty(obj: any) {
    return typeof obj == "undefined" || obj == null || obj == "";
  }
  checkParam() {
    if (this.isEmpty(this.chooseSchoole)) {
      this.$toptips.show(new ToastOptions("请选择学校"));
      return false;
    }
    if (this.isEmpty(this.params.uniacadaId) || this.params.uniacadaId === -1) {
      this.$toptips.show(new ToastOptions("请选择院系"));
      return false;
    }
    if (this.isEmpty(this.params.className)) {
      this.$toptips.show(new ToastOptions("请输入班级"));
      return false;
    }
    if (this.isEmpty(this.params.courseName)) {
      this.$toptips.show(new ToastOptions("请课程名称"));
      return false;
    }
    if (this.isEmpty(this.params.examArrangement)) {
      this.$toptips.show(new ToastOptions("请课程考试安排"));
      return false;
    }
    if (this.isEmpty(this.params.semester)) {
      this.$toptips.show(new ToastOptions("请选择学期"));
      return false;
    }

    return true;
  }
  createCourse() {
    if (!this.checkParam()) {
      return;
    }
    API.createCourse(this.params)
      .then((res) => {
        this.$router.push({ name: "Home" });
      })
      .catch((res) => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  cancel() {
    this.$router.go(-1);
  }
  loadschool() {
    API.listUniByName(this.chooseSchoole)
      .then((res) => {
        this.departments = res.data;
      })
      .catch((res) => {
        this.departments = [];
        this.$toptips.show(new ToastOptions(res.msg));
      });
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
.button {
  margin: 15px auto;
  padding: 15px;
  text-align: center;
}
.button .button-sp-area .weui-btn {
  margin: auto 15px;
}
</style>
