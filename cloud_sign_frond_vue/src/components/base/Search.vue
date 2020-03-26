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
    <div
      class="weui-cells searchbar-result"
      id="searchResult"
      v-show="isShowResult"
      style=" transform-origin: 0px 0px; opacity: 1; transform: scale(1, 1);"
    >
      <div
        class="weui-cell weui-cell_active weui-cell_access"
        @click="chooseText('输入测试文本')"
      >
        <div class="weui-cell__bd weui-cell_primary">
          <p>实时搜索文本</p>
        </div>
      </div>
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
      console.log("search text:" + this.searchInput);
      this.isShowResult = true;
    }
  }
  starSearch() {
    console.log("start search");
    this.isSearchFocus = true;
    (this.$refs.searchRef as HTMLInputElement).focus();
    this.isShowSearchText = false;
  }
  clearInput() {
    this.searchInput = "";
    this.isShowResult = false;
    (this.$refs.searchRef as HTMLInputElement).focus();
  }
  cancelSearch() {
    if (this.searchInput !== undefined && this.searchInput.length > 0) {
      return;
    }
    this.isSearchFocus = false;
    this.isShowSearchText = true;
  }
  submit() {
    this.$emit("search", this.searchInput);
    console.log(this.searchInput);
  }
  chooseText(chooseText: string) {
    console.log("text:" + chooseText);
  }
}
</script>
