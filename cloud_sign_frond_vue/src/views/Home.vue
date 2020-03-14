<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <kmDialog title="这是一个测试dialog" content="测试展示内容" buttons />
  </div>
</template>

<script>
// @ is an alias to /src
import { Component, Vue } from "vue-property-decorator";
import kmDialog from "../components/dialog/km-dialog.vue";

@Component({
  components: {
    kmDialog
  }
})
export default class Home extends Vue {}
</script>
