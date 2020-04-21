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
            v-show="signState.step === 2 || signState.step === 4"
            href="javascript:"
            @click="startSign"
            class="weui-btn weui-btn_mini weui-btn_primary"
            >发起签到</a
          >
          <a
            v-show="signState.step === 5"
            href="javascript:"
            class="weui-btn weui-btn_mini weui-btn_primary"
            @click="signIn"
            >签到</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

const START = 1;
const REDRAW = 2;
const CHECK = 3;
const REDRAW_FAILED = 4;
const CHECK_SUCCESS = 5;
const CHECK_FAILED = 6;

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
  private title = {
    text: "",
    color: "",
  };
  private canvas?: HTMLCanvasElement;
  private ctx?: CanvasRenderingContext2D;
  private r = 0; // 公式计算
  private touchDown = false;
  private devicePixelRatio = 0;

  private chooseType = 1;
  private lastPoint: Array<Position> = [];
  private circleInfo: Array<Position> = [];
  private restPoint: Array<Position> = [];
  private signState = { step: START, password: Array<Position>() };

  constructor() {
    super();
  }
  mounted() {
    this.canvas = this.$refs.signCanvas as HTMLCanvasElement;
    this.ctx = this.canvas.getContext("2d") as CanvasRenderingContext2D;
    this.r = this.ctx.canvas.width / (2 + 4 * 3); // 公式计算
    this.setChooseType(3);
  }
  init() {
    this.initDom();
    this.lastPoint = [];
    this.touchDown = false;
    this.resetCanvas();
    this.setShowTitle();
  }
  signIn() {
    console.log(this.signState.password.map((e) => e.index).join(""));
    this.resetAll();
  }
  startSign() {
    console.log(this.signState.password.map((e) => e.index).join(""));
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
    for (let i = 0; i < this.lastPoint.length; i++) {
      this.ctx.fillStyle = style;
      this.ctx.beginPath();
      this.ctx.arc(
        this.lastPoint[i].x,
        this.lastPoint[i].y,
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
    for (let i = 0; i < this.lastPoint.length; i++) {
      this.ctx.strokeStyle = type;
      this.ctx.beginPath();
      this.ctx.arc(
        this.lastPoint[i].x,
        this.lastPoint[i].y,
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
    lastPoint: Array<Position>
  ) {
    if (!this.ctx) {
      return;
    }
    //style:颜色 解锁轨迹
    this.ctx.beginPath();
    this.ctx.strokeStyle = style;
    this.ctx.lineWidth = 3;
    this.ctx.moveTo(this.lastPoint[0].x, this.lastPoint[0].y);
    for (let i = 1; i < this.lastPoint.length; i++) {
      this.ctx.lineTo(this.lastPoint[i].x, this.lastPoint[i].y);
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
    const n = this.chooseType;
    let count = 0;
    this.r = this.ctx.canvas.width / (2 + 4 * n); // 公式计算
    this.lastPoint = [];
    this.circleInfo = [];
    this.restPoint = [];
    const r = this.r;
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        count++;
        const obj = {
          x: j * 4 * r + 3 * r,
          y: i * 4 * r + 3 * r,
          index: count,
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
    this.drawLine("#27AED5", po, this.lastPoint); // 每帧画圆心
    for (let i = 0; i < this.restPoint.length; i++) {
      if (
        Math.abs(po.x - this.restPoint[i].x) < this.r &&
        Math.abs(po.y - this.restPoint[i].y) < this.r
      ) {
        this.drawPoint("#27AED5");
        this.lastPoint.push(this.restPoint[i]);
        this.restPoint.splice(i, 1);
        break;
      }
    }
  }
  checkPass(psw1: Array<Position>, psw2: Array<Position>) {
    // 检测密码
    let p1 = "",
      p2 = "";
    for (let i = 0; i < psw1.length; i++) {
      p1 += psw1[i].index + psw1[i].index;
    }
    for (let i = 0; i < psw2.length; i++) {
      p2 += psw2[i].index + psw2[i].index;
    }
    return p1 === p2;
  }
  storePass(psw: Array<Position>) {
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
        this.signState.password;
        this.signState.step = CHECK;
      } else {
        this.drawStatusPoint("red");
        this.drawPoint("red");
        this.signState.step = REDRAW_FAILED;
      }
    } else if (
      this.signState.step === CHECK ||
      this.signState.step === CHECK_FAILED ||
      this.signState.step === CHECK_SUCCESS
    ) {
      if (this.checkPass(this.signState.password, psw)) {
        this.drawStatusPoint("#2CFF26"); //小点点外圈高亮
        this.drawPoint("#2CFF26");
        this.drawLine(
          "#2CFF26",
          this.lastPoint[this.lastPoint.length - 1],
          this.lastPoint
        ); // 每帧画圆心
        this.signState.step = CHECK_SUCCESS;
      } else {
        this.drawStatusPoint("red");
        this.drawPoint("red");
        this.drawLine(
          "red",
          this.lastPoint[this.lastPoint.length - 1],
          this.lastPoint
        ); // 每帧画圆心
        this.signState.step = CHECK_FAILED;
      }
    }
  }
  setShowTitle() {
    if (this.signState.step === START) {
      this.title.text = "请输入签到手势";
      this.title.color = "gray";
    } else if (this.signState.step === REDRAW) {
      this.title.text = "请再次输入签到手势";
      this.title.color = "gray";
    } else if (this.signState.step === REDRAW_FAILED) {
      this.title.text = "两次签到手势不一致，重新输入";
      this.title.color = "red";
    } else if (this.signState.step === CHECK) {
      this.title.text = "请输入手势签到";
      this.title.color = "gray";
    } else if (this.signState.step === CHECK_FAILED) {
      this.title.text = "手势错误,签到失败";
      this.title.color = "red";
    } else if (this.signState.step === CHECK_SUCCESS) {
      this.title.color = "#2CFF26";
      this.title.text = "签到成功";
    } else {
      console.log(this.signState.step);
      this.title.color = "red";
      this.title.text = "未知签到状态";
    }
  }
  setChooseType(type: number) {
    this.chooseType = type;
    this.init();
  }
  showReset() {
    return this.signState.step === REDRAW;
  }
  initDom() {
    if (!this.canvas) {
      return;
    }
    this.chooseType = Number(window.localStorage.getItem("chooseType")) || 3;
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
    window.localStorage.removeItem("passwordxx");
    window.localStorage.removeItem("chooseType");
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
        this.lastPoint.push(this.circleInfo[i]);
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
  canvasTrouchend(e: TouchEvent) {
    if (this.touchDown) {
      this.touchDown = false;
      this.storePass(this.lastPoint);
      this.setShowTitle();
      if (this.signState.step !== CHECK_SUCCESS) {
        console.log("reset");
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
</style>
