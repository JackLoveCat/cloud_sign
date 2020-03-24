<template>
  <div class="home">
    <canvas id="sakura" />
    <div class="content">
      <div class="home-header">
        <router-link class="link" to="/home">
          <img
            class="home-logo"
            src="../assets/userLogo.jpeg"
            alt="到云 logo"
          />
        </router-link>
      </div>
      <div class="home-body">
        <div class="list">
          <router-link class="link" to="/articles">
            文章
          </router-link>
          <router-link class="link" to="/project">
            项目
          </router-link>
        </div>
        <div class="introduce">时光正好，未来可期，加油 ！</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
// 满屏落花效果
import { Component, Vue } from "vue-property-decorator";
declare var document: Document | any;

@Component({})
export default class Home extends Vue {
  mounted() {}
}
</script>
<style lang="less" scoped>
.home {
  background-color: gray;
  height: 100%;
  width: 100%;
  position: fixed;
  left: 0;
  top: 0;
  .content {
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    font-size: 20px;
    text-align: center;
    padding-top: 12%;
    .home-logo {
      width: 220px;
      border-radius: 50%;
    }
    .home-header {
      .link {
        display: block;
      }
    }
    .home-body {
      padding-top: 20px;
      .list {
        .link {
          display: inline-block;
          padding: 20px;
          color: #409eff;
          min-width: 80px;
        }
        .link:hover {
          color: rgb(155, 35, 35);
        }
      }
    }
    .introduce {
      padding-top: 20px;
      color: #fff;
    }
  }
}
</style>
