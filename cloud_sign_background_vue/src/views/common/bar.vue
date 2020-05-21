<template>
  <div></div>
</template>
<script>
let echarts = require("echarts/lib/echarts");
require("echarts/lib/chart/bar");
require("echarts/lib/component/tooltip");
export default {
  name: "bar-line",
  props: {
    width: {
      type: String,
      default: ""
    },
    height: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      optionData: [],
      legendData: [],
      xAxisData: [
        "中国财经大学",
        "中国政法大学",
        "中国人民大学",
        "武汉大学",
        "北京大学"
      ]
    };
  },
  methods: {
    initChart() {
      this.charts = echarts.init(this.$el);
      this.charts.setOption({
        tooltip: {
          trigger: "axis"
        },
        // grid: {
        //   left: "100",
        //   right: "10",
        //   bottom: "30",
        //   top: "10"
        // },
        legend: {},
        xAxis: {
          type: "category",
          boundaryGap: true,
          data: this.xAxisData,
          axisLabel: {
            interval: 0
          }
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "总量",
            type: "bar",
            stack: "总量",
            data: [120, 132, 101, 134, 90]
          }
        ]
      });
    }
  },
  mounted() {
    this.$el.style.width = this.width;
    this.$el.style.height = this.height;
    this.initChart();
  }
};
</script>
