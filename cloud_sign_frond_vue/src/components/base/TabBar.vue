<template>
  <div class="page tabbar">
    <div class="page_bd">
      <div class="weui-tab">
        <div class="weui-tab__panel">
          <component :is="currentView"></component>
        </div>
        <div class="weui-tabbar">
          <div
            v-for="(tab, i) in params"
            v-bind:key="i"
            :class="[
              i == active
                ? 'weui-tabbar__item  weui-bar__item_on'
                : 'weui-tabbar__item'
            ]"
          >
            <div
              style="display: inline-block; position: relative;"
              @click="changeTab(i)"
            >
              <img :src="tab.icon" :alt="tab.name" class="weui-tabbar__icon" />
              <span
                class="weui-badge"
                style="position: absolute; top: -2px; right: -13px;"
                v-show="tab.msgCount && tab.msgCount > 0 && tab.msgType === 1"
                >{{ tab.msgCount }}</span
              >
              <span
                class="weui-badge weui-badge_dot"
                style="position: absolute; top: 0; right: -6px;"
                v-show="tab.msgType === 2"
              ></span>
            </div>
            <p class="weui-tabbar__label">{{ tab.name }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import Class from "@/views/base/Class.vue";
import My from "@/views/base/My.vue";
import { TabBarConfig } from "@/types/config/TabBar";

@Component({
  components: { Class, My }
})
export default class TabBar extends Vue {
  private currentView = "Class";
  private active = 0;
  @Prop({
    type: Array,
    required: true,
    default: () => [
      {
        name: "班课",
        icon: require("../../assets/imgs/icon/icon_tabbar.png"),
        msgType: 1,
        msgCount: 0,
        view: "Class"
      },
      {
        name: "我的",
        icon: require("../../assets/imgs/icon/icon_tabbar.png"),
        msgType: 0,
        view: "My"
      }
    ]
  })
  params!: Array<TabBarConfig>;
  changeTab(index: number) {
    this.active = index;
    this.currentView = this.params[index].view;
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import url("../../less/base/variable/global.less");

.page_bd {
  width: 100%;
  height: 100vh;
}
</style>
