<template>
  <div class="masks">
    <div class="gesturePwd">
      <div style="position: absolute;top:0;left:0;right:0;bottom:0;">
        <h4 class="gestureTitle" :style="{ color: title.color }">
          {{ title.text }}
        </h4>
        <canvas
          ref="signCanvas"
          @touchstart="canvasTouchstart"
          @touchmove="canvasTouchmove"
          @touchend="canvasTrouchend"
          style="background-color:#000; display: inline-block; margin-top: 76px; width: 320px; height: 320px;"
        ></canvas>
        <div class="button-sp-area">
          <a
            v-show="signState.step === 7"
            href="javascript:"
            @click="endSign"
            class="weui-btn weui-btn_mini weui-btn_primary"
            >结束</a
          >
          <a
            href="javascript:"
            class="weui-btn weui-btn_mini weui-btn_primary"
            @click="goBack"
            >返回</a
          >
          <div class="location-text">您当前的位置：{{ location }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import BaiDuMap from "@/plugin/BMap.js";
import { Getter } from "vuex-class";
import cryptoJs from "crypto-js";
import API, { KResponse } from "@/utils/api";
import {
  ToastOptions,
  SuccessToastOptions
} from "../components/base/toast/index";
const COLUMN_NUM = 3; // 九宫格行数 列数
// 手势签到状态
const START = 1; // 开始准备画手势
const REDRAW = 2; // 确认手势
const REDRAW_FAILED = 3; // 确认手势错误
const CHECK = 4; // 检查手势
const CHECK_SUCCESS = 5; // 手势签到成功
const CHECK_FAILED = 6; // 手势签到失败
const READY_END_SIGN = 7; // 等待结束签到
const SIGN_NOT_START = 8; // 签到未开始
const SERVER_CHECK_FAILED = 9;

class Position {
  x: number;
  y: number;
  index = 1;
  public constructor(x: number, y: number, index = -1) {
    this.x = x;
    this.y = y;
    this.index = index;
  }
}
@Component
export default class Sign extends Vue {
  @Getter("User/getUserInfo") userInfo: any;
  private title = {
    text: "",
    color: ""
  };
  private canvas?: HTMLCanvasElement;
  private ctx?: CanvasRenderingContext2D;
  private r = 0; // 公式计算
  private touchDown = false;
  private devicePixelRatio = 0;
  private drawPath: Array<Position> = [];
  private circleInfo: Array<Position> = [];
  private restPoint: Array<Position> = [];
  private signState = { step: START, password: "" };
  private location: string | undefined | unknown = "未知位置";

  constructor() {
    super();
  }
  mounted() {
    if (!this.$route.query.myClassId) {
      this.$toptips.show(new ToastOptions("未知参数"));
      return;
    }
    this.canvas = this.$refs.signCanvas as HTMLCanvasElement;
    this.ctx = this.canvas.getContext("2d") as CanvasRenderingContext2D;
    this.r = this.ctx.canvas.width / (2 + 4 * 3); // 公式计算
    this.init();
    this.getLocation();
  }
  locationSuccess(point: any) {
    console.log(point.point);
  }
  getLocation() {
    BaiDuMap.init().then((name: string | undefined | unknown) => {
      this.location = name;
    });
  }
  init() {
    this.initDom();
    this.drawPath = [];
    this.touchDown = false;
    this.resetCanvas();
    this.setShowTitle();
    // TODO:根据课程查询签到活动 根据角色不同做不同处理
    API.getSignInfo(this.$route.query.myClassId as string)
      .then((res: KResponse) => {
        if (this.userInfo.roleid === 2 || this.userInfo.roleid === 1) {
          //教师
          if (!res.data || res.data.gesture === undefined) {
            this.signState.step = START;
          } else {
            this.signState.step = READY_END_SIGN;
            this.signState.password = res.data.gesture;
            // console.log("老师已经开始签到");
          }
        } else {
          if (!res.data || res.data.gesture === undefined) {
            this.signState.step = SIGN_NOT_START;
          } else {
            this.signState.step = CHECK;
            this.signState.password = res.data.gesture;
            // console.log("学生准备签到");
          }
        }
        this.setShowTitle();
      })
      .catch((res: KResponse) => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  endSign() {
    //TODO 老师请求服务器结束签到
    // console.log(this.signState.password);
    API.teacherSignInStop(this.$route.query.myClassId as string)
      .then((res: KResponse) => {
        this.$toptips.show(new SuccessToastOptions("签到结束"));
        this.$router.go(-1);
      })
      .catch((res: KResponse) => {
        this.$toptips.show(new ToastOptions(res.msg));
      });
  }
  userOperationEnd() {
    if (this.userInfo.roleid === 2) {
      // TODO:根据课程查询签到活动 根据角色不同做不同处理
      API.teacherSignIn(
        this.$route.query.myClassId as string,
        this.signState.password,
        this.location
      )
        .then((res: KResponse) => {
          this.$toptips.show(new SuccessToastOptions("签到发起成功~"));
        })
        .catch((res: KResponse) => {
          this.$toptips.show(new ToastOptions(res.msg));
        });
    } else {
      // TODO:根据课程查询签到活动 根据角色不同做不同处理
      API.studentSignIn(
        this.$route.query.myClassId as string,
        this.signState.password,
        this.location
      )
        .then((res: KResponse) => {
          this.$toptips.show(new SuccessToastOptions("签到成功"));
          setTimeout(() => {
            this.$router.go(-1);
          }, 2000);
        })
        .catch((res: KResponse) => {
          this.signState.step = SERVER_CHECK_FAILED;
          this.title.text = res.msg;
          this.$toptips.show(new ToastOptions(res.msg));
          this.resetCanvas();
          this.setShowTitle();
        });
    }
    // console.log("user operation end");
  }
  goBack() {
    // console.log(this.signState.password);
    this.$router.go(-1);
  }
  drawCle(x: number, y: number) {
    if (!this.ctx) {
      return;
    }
    // 初始化解锁密码面板 小圆圈
    this.ctx.strokeStyle = "#87888a"; //密码的点点默认的颜色
    this.ctx.lineWidth = 2;
    this.ctx.beginPath();
    this.ctx.arc(x, y, this.r, 0, Math.PI * 2, true);
    this.ctx.closePath();
    this.ctx.stroke();
  }
  drawPoint(style: string | CanvasGradient | CanvasPattern) {
    if (this.ctx === undefined) {
      return;
    }
    // 初始化圆心
    for (let i = 0; i < this.drawPath.length; i++) {
      this.ctx.fillStyle = style;
      this.ctx.beginPath();
      this.ctx.arc(
        this.drawPath[i].x,
        this.drawPath[i].y,
        this.r / 2.5,
        0,
        Math.PI * 2,
        true
      );
      this.ctx.closePath();
      this.ctx.fill();
    }
  }
  drawStatusPoint(type: string | CanvasGradient | CanvasPattern) {
    if (!this.ctx) {
      return;
    }
    // 初始化状态线条
    for (let i = 0; i < this.drawPath.length; i++) {
      this.ctx.strokeStyle = type;
      this.ctx.beginPath();
      this.ctx.arc(
        this.drawPath[i].x,
        this.drawPath[i].y,
        this.r,
        0,
        Math.PI * 2,
        true
      );
      this.ctx.closePath();
      this.ctx.stroke();
    }
  }
  drawLine(
    style: string | CanvasGradient | CanvasPattern,
    po: Position,
    drawPath: Array<Position>
  ) {
    if (!this.ctx) {
      return;
    }
    //style:颜色 解锁轨迹
    this.ctx.beginPath();
    this.ctx.strokeStyle = style;
    this.ctx.lineWidth = 3;
    this.ctx.moveTo(this.drawPath[0].x, this.drawPath[0].y);
    for (let i = 1; i < this.drawPath.length; i++) {
      this.ctx.lineTo(this.drawPath[i].x, this.drawPath[i].y);
    }
    this.ctx.lineTo(po.x, po.y);
    this.ctx.stroke();
    this.ctx.closePath();
  }
  resetCanvas() {
    if (!this.ctx) {
      return;
    }
    // 创建解锁点的坐标，根据canvas的大小来平均分配半径
    let count = 0;
    this.r = this.ctx.canvas.width / (2 + 4 * COLUMN_NUM); // 公式计算
    this.drawPath = [];
    this.circleInfo = [];
    this.restPoint = [];
    const r = this.r;
    for (let i = 0; i < COLUMN_NUM; i++) {
      for (let j = 0; j < COLUMN_NUM; j++) {
        count++;
        const obj = {
          x: j * 4 * r + 3 * r,
          y: i * 4 * r + 3 * r,
          index: count
        };
        this.circleInfo.push(obj);
        this.restPoint.push(obj);
      }
    }
    this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    for (let i = 0; i < this.circleInfo.length; i++) {
      this.drawCle(this.circleInfo[i].x, this.circleInfo[i].y);
    }
    //return arr;
  }
  getPosition(e: TouchEvent) {
    // 获取touch点相对于canvas的坐标
    const rect = (e.currentTarget as HTMLCanvasElement).getBoundingClientRect();
    const po = new Position(
      (e.touches[0].clientX - rect.left) * this.devicePixelRatio,
      (e.touches[0].clientY - rect.top) * this.devicePixelRatio
    );
    return po;
  }
  update(po: Position) {
    if (!this.ctx) {
      return;
    }
    // 核心变换方法在touchmove时候调用
    this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    for (let i = 0; i < this.circleInfo.length; i++) {
      // 每帧先把面板画出来
      this.drawCle(this.circleInfo[i].x, this.circleInfo[i].y);
    }
    this.drawPoint("#27AED5"); // 每帧花轨迹
    this.drawStatusPoint("#27AED5"); // 每帧花轨迹
    this.drawLine("#27AED5", po, this.drawPath); // 每帧画圆心
    for (let i = 0; i < this.restPoint.length; i++) {
      if (
        Math.abs(po.x - this.restPoint[i].x) < this.r &&
        Math.abs(po.y - this.restPoint[i].y) < this.r
      ) {
        this.drawPoint("#27AED5");
        this.drawPath.push(this.restPoint[i]);
        this.restPoint.splice(i, 1);
        break;
      }
    }
  }
  checkPass(psw1: string, psw2: string) {
    if (!psw1) {
      return false;
    }
    return psw1 === psw2;
  }
  updateCurrentStep(psw: string) {
    // touchend结束之后对密码和状态的处理
    if (this.signState.step === START) {
      this.signState.password = psw;
      this.signState.step = REDRAW;
    } else if (
      this.signState.step === REDRAW ||
      this.signState.step === REDRAW_FAILED
    ) {
      if (this.checkPass(this.signState.password, psw)) {
        this.drawStatusPoint("#2CFF26");
        this.drawPoint("#2CFF26");
        this.signState.password = psw;
        if (this.userInfo.roleid === 2) {
          this.signState.step = READY_END_SIGN;
        } else {
          this.signState.step = CHECK;
        }
      } else {
        this.drawStatusPoint("red");
        this.drawPoint("red");
        this.signState.step = REDRAW_FAILED;
      }
    } else if (
      this.signState.step === CHECK ||
      this.signState.step === CHECK_FAILED ||
      this.signState.step === CHECK_SUCCESS ||
      this.signState.step == SERVER_CHECK_FAILED
    ) {
      if (
        this.userInfo.roleid === 2
          ? this.checkPass(this.signState.password, psw)
          : this.checkPass(psw, psw)
      ) {
        this.drawStatusPoint("#2CFF26"); //小点点外圈高亮
        this.drawPoint("#2CFF26");
        this.drawLine(
          "#2CFF26",
          this.drawPath[this.drawPath.length - 1],
          this.drawPath
        ); // 每帧画圆心
        this.signState.step = CHECK_SUCCESS;
        this.signState.password = psw;
      } else {
        this.drawStatusPoint("red");
        this.drawPoint("red");
        this.drawLine(
          "red",
          this.drawPath[this.drawPath.length - 1],
          this.drawPath
        ); // 每帧画圆心
        this.signState.step = CHECK_FAILED;
      }
    }
  }
  setTeacherTitle() {
    if (this.signState.step === START) {
      this.title.text = "请输入签到手势";
      this.title.color = "gray";
    } else if (this.signState.step === REDRAW) {
      this.title.text = "请再次输入签到手势";
      this.title.color = "gray";
    } else if (this.signState.step === REDRAW_FAILED) {
      this.title.text = "两次签到手势不一致，重新输入";
      this.title.color = "red";
    } else if (this.signState.step === READY_END_SIGN) {
      this.title.text = "等待学生签到，结束请点击结束按钮";
      this.title.color = "gray";
    } else {
      // console.log(this.signState.step);
      this.title.color = "red";
      this.title.text = "未知签到状态";
    }
  }
  setStudentTitle() {
    if (this.signState.step === CHECK) {
      this.title.text = "请输入手势签到";
      this.title.color = "gray";
    } else if (this.signState.step === CHECK_FAILED) {
      this.title.text = "手势错误,请重新输入";
      this.title.color = "red";
    } else if (this.signState.step === SERVER_CHECK_FAILED) {
      return (this.title.color = "red");
    } else if (this.signState.step === CHECK_SUCCESS) {
      this.title.color = "#2CFF26";
      this.title.text = "签到成功";
    } else if (this.signState.step === SIGN_NOT_START) {
      this.title.color = "red";
      this.title.text = "教师未开始签到";
    } else {
      // console.log(this.signState.step);
      this.title.color = "red";
      this.title.text = "未知签到状态";
    }
  }
  setShowTitle() {
    if (this.userInfo.roleid === 2) {
      this.setTeacherTitle();
    } else {
      this.setStudentTitle();
    }
  }
  showReset() {
    return this.signState.step === REDRAW;
  }
  initDom() {
    if (!this.canvas) {
      return;
    }
    this.devicePixelRatio = window.devicePixelRatio || 1;
    const width = 320;
    const height = 320;
    // 高清屏锁放
    this.canvas.style.width = width + "px";
    this.canvas.style.height = height + "px";
    this.canvas.height = height * this.devicePixelRatio;
    this.canvas.width = width * this.devicePixelRatio;
  }

  resetAll() {
    this.signState.step = START;
    this.setShowTitle();
    this.resetCanvas();
  }
  canvasTouchstart(e: TouchEvent) {
    e.preventDefault(); // 某些android 的 touchmove不宜触发 所以增加此行代码
    const po = this.getPosition(e);
    for (let i = 0; i < this.circleInfo.length; i++) {
      if (
        Math.abs(po.x - this.circleInfo[i].x) < this.r &&
        Math.abs(po.y - this.circleInfo[i].y) < this.r
      ) {
        this.touchDown = true;
        this.drawPoint("#27AED5");
        this.drawPath.push(this.circleInfo[i]);
        this.restPoint.splice(i, 1);
        break;
      }
    }
  }
  canvasTouchmove(e: TouchEvent) {
    if (this.touchDown) {
      this.update(this.getPosition(e));
    }
  }
  conver2Pass(drawPath: Array<Position>): string {
    return drawPath.map(e => e.index).join("");
  }
  canvasTrouchend(e: TouchEvent) {
    if (this.touchDown) {
      this.touchDown = false;
      const drawPass = this.conver2Pass(this.drawPath);
      this.updateCurrentStep(drawPass);
      this.setShowTitle();
      if (
        this.signState.step === CHECK_SUCCESS ||
        this.signState.step === READY_END_SIGN
      ) {
        this.userOperationEnd();
      } else {
        // console.log("reset canvas");
        this.resetCanvas();
      }
    }
  }
}
</script>
<style lang="less">
.masks {
  text-align: center;
  position: fixed;
  z-index: 1000;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
}
.gesturePwd {
  position: fixed;
  z-index: 5000;
  width: 100%;
  height: 100%;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  background-color: #666;
  text-align: center;
  border-radius: 3px;
  overflow: hidden;
  background-color: #000;
}
.gestureTitle {
  color: #87888a;
  margin-top: 85px;
  font-size: 20px;
  font-weight: normal;
}
.location-text {
  color: #497837;
}
</style>
