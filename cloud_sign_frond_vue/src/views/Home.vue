<template>
  <TabBar :params="tabInfo"></TabBar>
</template>

<script lang="ts">
// @ is an alias to /src
import TabBar from "@/components/base/TabBar.vue";
import { Component, Vue } from "vue-property-decorator";

@Component({
  components: { TabBar },
})
export default class Home extends Vue {
  tabInfo = [
    {
      name: "班课",
      icon: require("../assets/imgs/icon/class.png"),
      msgType: 1,
      msgCount: 0,
      active: true,
      view: "Class",
    },
    {
      name: "我的",
      icon: require("../assets/imgs/icon/my.png"),
      msgType: 0,
      view: "My",
    },
  ];
}
</script>
