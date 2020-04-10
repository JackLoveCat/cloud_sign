<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 18:01:46
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 21:03:31
 -->
<template>
  <form @submit.prevent="submit">
    <slot></slot>
  </form>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { ValidateRules } from "@/types/config/Form";

@Component({})
export default class Class extends Vue {
  private rules: Map<String, ValidateRules> = new Map<String, ValidateRules>();

  goMyclass(classId: number) {
    this.$router.push({ name: "MyClass", params: { myClassId: "" + classId } });
  }
  validate(cb: Function) {
    //执行所有的FormItem的方法
    const tasks = this.$children
      .filter(v => v)
      .map(item => item.$emit("validate"));

    Promise.all(tasks)
      .then(() => cb(true))
      .catch(() => cb(false));
  }
  submit() {
    this.validate((valid: boolean) => {
      if (valid) {
        alert("校验成功");
      } else {
        alert("校验失败");
      }
    });
  }
}
</script>
