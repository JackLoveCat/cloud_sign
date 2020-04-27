<template>
  <div class="page__bd">
    <div class="weui-tab">
      <div class="weui-navbar">
        <div
          :class="{
            'weui-navbar__item': true,
            'weui-bar__item_on': index === 1
          }"
          @click="changeIndex(1)"
        >
          我创建的班课
        </div>
        <div
          :class="{
            'weui-navbar__item': true,
            'weui-bar__item_on': index === 2
          }"
          @click="changeIndex(2)"
        >
          我加入的班课
        </div>
      </div>
      <div class="weui-tab__panel">
        <Search @search="search"></Search>
        <div class="weui-panel weui-panel_access">
          <div class="weui-panel__bd">
            <a
              href="javascript:"
              class="weui-media-box weui-media-box_appmsg"
              v-for="cla in classes"
              v-bind:key="cla.courseId"
              @click="goMyclass(cla.courseId)"
            >
              <!-- <div class="weui-media-box__hd">
                <img class="weui-media-box__thumb" :src="cla.icon" alt="" />
              </div> -->
              <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">{{ cla.courseName }}</h4>
                <p class="weui-media-box__desc">
                  {{ cla.remark }}
                </p>
              </div>
            </a>
          </div>
          <a
            href="javascript:"
            class="weui-cell weui-cell_active weui-cell_link"
            @click="createOrJoin"
          >
            <div class="weui-cell__bd">
              {{ index === 1 ? "创建班课" : "加入班课" }}
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
import Api from "@/utils/api";
@Component({
  components: {
    Search
  }
})
export default class Class extends Vue {
  private index = 1;
  private classes: Array<CourseInfo> = [];
  constructor() {
    super();
    this.changeIndex(1);
  }
  changeIndex(index: number) {
    this.index = index;
    if (index === 1)
      Api.getMyCreate({ test: 1 })
        .then(res => {
          this.classes = res.rows as Array<CourseInfo>;
        })
        .catch(res => {
          console.log(res);
        });
    else
      Api.getMyJoin({ test: 1 })
        .then(res => {
          this.classes = res.rows as Array<CourseInfo>;
        })
        .catch(res => {
          console.log(res);
        });
  }
  goMyclass(classId: number) {
    this.$router.push({ name: "MyClass", query: { myClassId: "" + classId } });
  }
  createOrJoin() {
    if (this.index === 1) {
      this.$router.push({ name: "CourseCreate" });
    } else {
      this.$router.push({ name: "CourseJoin" });
    }
  }
  search(text: string) {
    console.log("parent search:" + text);
  }
}
</script>
