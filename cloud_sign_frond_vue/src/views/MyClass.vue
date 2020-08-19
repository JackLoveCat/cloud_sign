<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-25 01:07:54
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-05-06 23:23:27
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
          <span style="vertical-align: middle;">签到</span>
        </div>
        <div class="weui-cell__ft"></div>
      </div>
    </div>
    <!--
    <div class="weui-panel">
      <div class="weui-panel__hd">学生列表</div>
      <div class="weui-panel__bd">
        <div class="weui-media-box weui-media-box_small-appmsg">
          <div class="weui-cells">
            <a
              class="weui-cell weui-cell_active weui-cell_access weui-cell_example"
              href="javascript:"
            >
              <div class="weui-cell__bd weui-cell_primary">
                <p>jack</p>
              </div>
            </a>
            <a
              class="weui-cell weui-cell_active weui-cell_access weui-cell_example"
              href="javascript:"
            >
              <div class="weui-cell__hd">
                <img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
                  alt=""
                  style="width: 20px; height: 20px; margin-right: 16px; display: block;"
                />
              </div>
              <div class="weui-cell__bd weui-cell_primary">
                <p>Jack</p>
              </div>
              <span class="weui-cell__ft"></span>
            </a>
          </div>
        </div>
      </div>
    </div>
    -->
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Api, { KResponse } from "@/utils/api";
import { ToastOptions } from "../components/base/toast/index";
@Component
export default class MyClass extends Vue {
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
      query: { myClassId: this.$route.query.myClassId },
    });
  }
}
</script>
