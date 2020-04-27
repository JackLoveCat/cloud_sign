<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-04-18 09:54:26
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-04-18 11:24:08
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
                  <label class="weui-label">班课数量</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="courseNum"
                    v-model="params.courseNum"
                    type="number"
                    pattern="[0-9]*"
                    class="weui-input"
                    placeholder="请输入班课数量"
                  />
                </div>
              </div>
              <div class="weui-cell weui-cell_active">
                <div class="weui-cell__hd">
                  <label class="weui-label">课程首页</label>
                </div>
                <div class="weui-cell__bd">
                  <input
                    id="coursePage"
                    v-model="params.coursePage"
                    class="weui-input"
                    placeholder="请输入课程首页"
                  />
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
                  <label class="weui-label">课程描述</label>
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
    courseNum: "",
    coursePage: "",
    curriculum: "",
    dataScope: "",
    examArrangement: "",
    lectureProgress: "",
    remark: "",
    semester: "",
    studyRequirement: "",
    textbook: "",
    uniacada_id: 1
  };
  createCourse() {
    API.createCourse(this.params)
      .then(res => {
        console.log(res);
      })
      .catch(res => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  cancel() {
    this.$router.go(-1);
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
