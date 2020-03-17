<template>
  <div>
    <el-alert
      title="温馨提示："
      type="warning"
      :closable="false">
      <div slot-scope="description">
        <p class="el-alert__description">仅提供过去一周，一月，以及一学期的数据</p>
      </div>
    </el-alert>

    <el-select v-model="value" placeholder="请选择时长查看统计情况" class="selectDate"  @change="getMyData">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
  import echarts from 'echarts'
  export default {
    data() {
      return {
        options: [{
          value: 'week',
          label: '过去七天'
        }, {
          value: 'month',
          label: '过去一月'
        }, {
          value: 'term',
          label: '过去一学期'
        }],
        value: 'week'
      }
    },
    methods:{
      //根据用户选项获取对应时长的统计数据
      getMyData(val){
         console.log(val)
      }
    }
  }
</script>

<style lang="scss">
  .mod-demo-echarts {
    > .el-alert {
      margin-bottom: 10px;
    }
    > .el-row {
      margin-top: -10px;
      margin-bottom: -10px;
      .el-col {
        padding-top: 10px;
        padding-bottom: 10px;
      }
    }
    .chart-box {
      max-width: 500px;
      min-height: 400px;
    }
  }
  .selectDate{
    margin-top: 20px;
  }
</style>
