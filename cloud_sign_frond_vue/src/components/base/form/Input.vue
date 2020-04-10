<!--
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 17:57:24
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 21:05:50
 -->
<template>
  <input>
  </input>
</template>
<script>
export default {
  provide() {
    return {
      form: this
    };
  },
  methods: {
    validate(cb) {
      //执行所有的FormItem的方法
      const tasks = this.$children
        .filter(v => v.prop)
        .map(item => item.validate());
      Promise.all(tasks)
        .then(() => cb(true))
        .catch(() => cb(false));
    }
  }
};
</script>
