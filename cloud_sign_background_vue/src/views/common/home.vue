<template>
  <div class="homepage-container">
    <div class="home-total">
      <div class="home-total-item" v-for="(item, index) of homeTotalData">
        <div class="wrapper-item">
          <p class="title">{{ item.title }}</p>
          <p class="value digital-number" ref="countup">{{ item.value }}</p>
          <color-line
            :id="'main' + index"
            :color="item.color"
            :optionData="item.data"
            width="180px"
            height="70px"
          ></color-line>
        </div>
      </div>
    </div>

    <el-row class="home-part1" :gutter="20">
      <el-col :span="16">
        <div class="near-six-month">
          <div class="title">
            <p class="title-value">院校排行榜</p>
          </div>
          <div class="content" ref="near-six-month-chart">
            <bar-line width="100%" height="100%"></bar-line>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="rank">
          <div class="title">
            <p class="title-value">院校排行榜</p>
          </div>
          <div class="content" ref="rankContent">
            <ul class="wrapper-user">
              <li v-for="(item, idx) in rankList" :key="idx" class="user-item">
                <div class="user-info">
                  <p class="name">{{ item.name }}</p>
                  <p style="width:200px;background:#ccc;height:22px;">
                    <span
                      style="display:inline-block;background:blue;height:22px"
                      :style="{ width: (item.value / 577) * 100 + '%' }"
                    ></span>
                  </p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ColorLine from "@/components/color-line";
import barLine from "./bar";
import BScroll from "better-scroll";
// import InvestmentPie from "@/views/homepage/investment-pie";
// import FinancingPie from "@/views/homepage/financing-pie";
export default {
  components: {
    ColorLine,
    barLine
    // InvestmentPie,
    // FinancingPie
  },
  data() {
    return {
      homeTotalData: [
        {
          title: "新增用户",
          value: 523,
          color: "#79f2e9",
          data: [96, 94, 26, 64, 93, 47, 14]
        },
        {
          title: "用户访问量",
          value: 600,
          color: "#f2c579",
          data: [8, 116, 33, 109, 88, 16, 79]
        },
        {
          title: "签到率",
          value: "20%",
          color: "#a279f2",
          data: [66, 109, 48, 85, 27, 18, 102]
        },
        {
          title: "最近访问数",
          value: 50,
          color: "#79f27f",
          data: [81, 2, 13, 93, 56, 100, 30]
        }
      ],
      homeDetailItem: [],
      rankList: [
        {
          name: "中国财经大学",
          value: 120
        },
        {
          name: "中国政法大学",
          value: 132
        },
        {
          name: "中国人民大学",
          value: 101
        },
        {
          name: "武汉大学",
          value: 134
        },
        {
          name: "北京大学",
          value: 90
        }
      ],
      numAnim: null
    };
  },
  mounted() {
    this._initScroll();
  },
  methods: {
    _initScroll() {
      if (!this.scroll) {
        this.scroll = new BScroll(this.$refs.rankContent, {
          scrollY: true,
          click: true,
          scrollbar: {
            fade: false,
            interactive: true // 1.8.0 新增
          },
          mouseWheel: {
            speed: 20,
            invert: false,
            easeTime: 300
          }
        });
      } else {
        this.scroll.refresh();
      }
    }
  }
};
</script>

<style lang="scss">
.homepage-container {
  min-width: 800px;
  .home-total {
    width: 100%;
    min-width: 800px;
    height: 160px;
    border: 1px solid #ddd;
    border-radius: 4px;
    margin: 0 0 15px 0;
    .home-total-item {
      box-sizing: border-box;
      display: inline-block;
      width: 25%;
      height: 100%;
      padding: 15px 0;
      vertical-align: top;
      .wrapper-item {
        height: 100%;
        padding: 0 20px;
        border-right: 1px solid #ccc;
        text-align: center;
        .title {
          margin: 0px 0;
        }
        .value {
          margin: 5px 0;
          font-size: 34px;
          color: #ffc107;
        }
      }
      &:last-child {
        .wrapper-item {
          border: none;
        }
      }
    }
  }
  .home-part1 {
    margin: 0 !important;
    .near-six-month {
      border: 1px solid #eee;
      height: 300px;
      .title {
        background: #dde3ef;
        padding: 10px 0;
        .title-value {
          margin-left: 4px;
          text-indent: 4px;
          color: #666;
          &:before {
            display: inline-block;
            content: "";
            width: 4px;
            height: 16px;
            background: purple;
            margin-right: 4px;
            border-radius: 4px;
            vertical-align: middle;
          }
        }
      }
      .content {
        width: 100%;
        height: 260px;
      }
    }
    .detail-item-wrapper {
      display: flex;
      height: 300px;
      overflow: hidden;
      flex-wrap: wrap;
      flex-flow: row wrap;
      justify-content: space-around;
      align-content: space-around;
      padding: 0 10px;
      color: #fff;
      .home-detail-item {
        flex: 0 0 48%;
        height: 145px;
        border: 1px solid #eee;
        background-image: linear-gradient(
          rgba(255, 255, 255, 0.1),
          rgba(255, 255, 255, 0.3)
        ) !important;
        cursor: pointer;
      }
      .home-detail-item:hover {
        background-image: none !important;
      }
      .home-detail-item:nth-child(3),
      .home-detail-item:nth-child(4) {
        margin-top: 10px;
      }
      .home-detail-item {
        .name {
          padding: 30px 0 10px 0;
          text-align: center;
          font-size: 20px;
        }
        .value {
          text-align: center;
          .num {
            font-size: 28px;
          }
        }
      }
    }
    .rank {
      .title {
        background: #dde3ef;
        padding: 10px 0;
        .title-value {
          margin-left: 4px;
          text-indent: 4px;
          color: #666;
          &:before {
            display: inline-block;
            content: "";
            width: 4px;
            height: 16px;
            background: purple;
            margin-right: 4px;
            border-radius: 4px;
            vertical-align: middle;
          }
        }
      }
      .content {
        position: relative;
        width: 100%;
        height: 260px;
        overflow: hidden;
        .wrapper-user {
          margin: 0;
          list-style: none;
          padding-left: 0;
          .user-item {
            padding: 5px;
            .avatar {
              border: 1px solid #888;
              border-radius: 100px;
              vertical-align: bottom;
            }
            .user-info {
              display: inline-block;
              padding-left: 5px;
              .name {
                color: #999;
                font-size: 14px;
              }
              .value {
                color: red;
              }
            }
          }
        }
      }
    }
  }
  .home-part2 {
    margin-top: 15px;
    .financing-sprinkled {
      border: 1px solid #eee;
      height: 350px;
      .title {
        background: #dde3ef;
        padding: 10px 0;
        .title-value {
          margin-left: 4px;
          text-indent: 4px;
          color: #666;
          &:before {
            display: inline-block;
            content: "";
            width: 4px;
            height: 16px;
            background: purple;
            margin-right: 4px;
            border-radius: 4px;
            vertical-align: middle;
          }
        }
      }
      .content {
        display: inline-flex;
        width: 100%;
        height: 310px;
        .investment {
          height: 310px;
          width: 50%;
          .title {
            display: inherit;
            text-align: center;
            background: transparent;
            padding-top: 20px;
          }
          .detail {
            margin-left: 10px;
            text-align: center;
            .detail-item {
              display: inline-block;
              width: 40%;
              margin: 5px;
              padding-left: 5px;
              border-left: 5px solid #ccc;
              color: #666;
            }
          }
        }
        .financing {
          height: 310px;
          width: 50%;
          .title {
            display: inherit;
            text-align: center;
            background: transparent;
            padding-top: 20px;
          }
          .detail {
            margin-left: 10px;
            text-align: center;
            .detail-item {
              display: inline-block;
              width: 40%;
              margin: 5px;
              padding-left: 5px;
              border-left: 5px solid #ccc;
              color: #666;
            }
          }
        }
      }
    }
    .bad-debt {
      height: 350px;
      min-width: 540px;
      margin-left: 10px;
      border: 1px solid #eee;

      .title {
        background: #dde3ef;
        padding: 10px 0;
        .title-value {
          margin-left: 4px;
          text-indent: 4px;
          color: #666;
          &:before {
            display: inline-block;
            content: "";
            width: 4px;
            height: 16px;
            background: purple;
            margin-right: 4px;
            border-radius: 4px;
            vertical-align: middle;
          }
        }
      }
      .content {
        height: inherit;
        .bad {
          height: 50%;
          padding: 20px 30px;
          .total {
            display: inline-block;
            width: 200px;
            color: #666;
            vertical-align: top;
            .total1 {
              text-align: center;
              .num {
                font-size: 24px;
              }
            }
            .total2 {
              text-align: center;
              margin-top: 20px;
              .num {
                font-size: 24px;
              }
            }
          }
          .chart {
            display: inline-block;
            margin-left: 15px;
            .title {
              background: none;
              border-bottom: 1px solid #ccc;
            }
            .line {
              border-bottom: 1px solid #ccc;
              padding-bottom: 30px;
              &:last-child {
                border-bottom-color: #000;
              }
            }
            &:after {
              content: "0";
              position: relative;
              font-size: 70px;
              left: 20px;
              top: -70px;
              color: #ddd;
            }
          }
        }
        .overdue {
          padding: 10px 30px;
          height: 50%;
          .total {
            display: inline-block;
            width: 200px;
            color: #666;
            vertical-align: top;
            .total1 {
              text-align: center;
              .num {
                font-size: 24px;
              }
            }
            .total2 {
              text-align: center;
              margin-top: 20px;
              .num {
                font-size: 24px;
              }
            }
          }
          .chart {
            display: inline-block;
            margin-left: 15px;
            .title {
              background: none;
              border-bottom: 1px solid #ccc;
            }
            .line {
              border-bottom: 1px solid #ccc;
              padding-bottom: 30px;
              &:last-child {
                border-bottom-color: #000;
              }
            }
            &:after {
              content: "0";
              position: relative;
              font-size: 70px;
              left: 20px;
              top: -70px;
              color: #ddd;
            }
          }
        }
      }
    }
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
