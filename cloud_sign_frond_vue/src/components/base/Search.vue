<template>
  <div class="page__bd">
    <div
      :class="
        isSearchFocus
          ? 'weui-search-bar'
          : 'weui-search-bar weui-search-bar_facusing'
      "
      id="searchBar"
    >
      <form class="weui-search-bar__form" @submit.prevent="submit">
        <div class="weui-search-bar__box">
          <i class="weui-icon-search"></i>
          <input
            type="search"
            class="weui-search-bar__input"
            id="searchInput"
            v-model="searchInput"
            placeholder="搜索"
            required=""
            ref="searchRef"
            @input="suggestSearch"
            @blur="cancelSearch"
          />
          <a
            href="javascript:"
            @click="clearInput"
            class="weui-icon-clear"
            id="clearInput"
          ></a>
        </div>
        <label
          class="weui-search-bar__label"
          id="searchText"
          v-show="isShowSearchText"
          @click="starSearch"
          style="transform-origin: 0px 0px; opacity: 1; transform: scale(1, 1);"
        >
          <i class="weui-icon-search"></i>
          <span>搜索</span>
        </label>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
@Component
export default class Search extends Vue {
  private searchInput = "";
  private isSearchFocus = false;
  private isShowSearchText = true;
  private isShowResult = false;
  suggestSearch() {
    if (this.searchInput !== undefined && this.searchInput.length > 0) {
      this.isShowResult = true;
    }
  }
  starSearch() {
    this.isSearchFocus = true;
    (this.$refs.searchRef as HTMLInputElement).focus();
    this.isShowSearchText = false;
  }
  clearInput() {
    this.$emit("reset");
    this.searchInput = "";
    this.isShowResult = false;
    this.isShowSearchText = false;
    (this.$refs.searchRef as HTMLInputElement).focus();
  }
  cancelSearch() {
    console.log("cancelsearch");
    this.searchInput = "";
    this.isSearchFocus = false;
    this.isShowSearchText = true;
    this.$emit("reset");
  }
  submit() {
    this.$emit("search", this.searchInput);
  }
  chooseText(chooseText: string) {
    this.$emit("search", chooseText);
  }
}
</script>
