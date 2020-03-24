<template>
  <div class="page__bd">
    <Search></Search>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Search from "@/components/base/Search.vue";

@Component({
  components: {
    Search
  }
})
export default class Class extends Vue {}
</script>
