<template>
  <div>
    <el-dialog
      title="该学生作业情况"
      :visible.sync="dialogVisible"
      width="50%"
      >
      <span>姓名：{{role.name}} </span>
      <span>学号：{{role.userName}}</span>
      <p>手机号：{{role.userPhone}} 专业：{{role.userMajor}}</p>
      <p>邮箱：{{role.userEmail}}</p>
      <div id="uploadWork" class="chart-box" style="width: 500px;height: 300px;margin:0 auto"></div>
      <div id="averScore" class="chart-box" style="width: 500px;height: 300px;margin:0 auto"></div>
      <div id="workScore" class="chart-box" style="width: 500px;height: 500px;margin:0 auto"></div>
    </el-dialog>
  </div>
</template>

<script>
  import echarts from 'echarts'
    export default {
      data(){
        return {
          dialogVisible:false,
          uploadWork:null,
          averScore:null,
          workScore:null,
          submitMap:[],
          averScoreMap:[],
          listInt:[],
          listString:[],
          role:{}
        }
      },
      activated () {
        // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
        if (this.uploadWork) {
          this.uploadWork.resize()
        }
        if (this.averScore) {
          this.averScore.resize()
        }
        if (this.workScore) {
          this.workScore.resize()
        }
      },
      methods:{
        init(sId){
          this.dialogVisible=true
          this.getStudentData(sId)
        },
        initUploadWork(){
          var option = {
            title : {
              text: '作业提交次数',
              subtext: '情况统计',
              x:'center'
            },
            tooltip : {
              trigger: 'item',
              formatter: "{a} <br/>{b}  : {c}次 ({d}%)"
            },
            legend: {
              orient: 'vertical',
              left: 'left',
              data: ['已提交','未提交']
            },
            color:['#91c7ae', '#c23531'],
            series : [
              {
                name: '作业情况',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:this.submitMap,
                itemStyle: {
                  emphasis: {
                    shadowBlur: 20,
                    shadowOffsetX: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.8)'
                  }
                }
              }
            ]
          }

          this.uploadWork = echarts.init(document.getElementById('uploadWork'))
          this.uploadWork.setOption(option)
          window.addEventListener('resize', () => {
            this.uploadWork.resize()
          })
        },
        //平均分
        initAverScore(){
          var option = {
            title : {
              text: '平均分',
              subtext: '',
              x:'center',

            },
            tooltip: {
              trigger: 'item',
              formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
              orient: 'vertical',
              x: 'left',
              data:['我的平均分','仍需努力的分数']
            },
            color:['#91c7ae', '#d48265'],
            series: [
              {
                name:'平均分统计',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                  normal: {
                    show: false,
                    position: 'center'
                  },
                  emphasis: {
                    show: true,
                    textStyle: {
                      fontSize: '18',
                      fontWeight: 'bold'
                    }
                  }
                },
                labelLine: {
                  normal: {
                    show: false
                  }
                },
                data:this.averScoreMap
              }
            ]
          }
          this.averScore = echarts.init(document.getElementById('averScore'))
          this.averScore.setOption(option)
          window.addEventListener('resize', () => {
            this.averScore.resize()
          })
        },
        //每次作业的分数情况
        initWorkScore(){
          var option = {
            title : {
              text: '每次作业得分',
              subtext: '',
              x:'left'
            },
            tooltip : {
              trigger: 'axis',
              axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
              }
            },
            legend: {
              data:['作业分数']
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            color:['#8ec9eb'],
            xAxis : [
              {
                type : 'category',
                data : this.listString,
                axisTick: {
                  alignWithLabel: true
                }
              }
            ],
            yAxis : [
              {
                type : 'value'
              }
            ],
            series : [
              {
                name:'作业分数',
                type:'bar',
                data:this.listInt
              },
            ]
          }
          this.workScore = echarts.init(document.getElementById('workScore'))
          this.workScore.setOption(option)
          window.addEventListener('resize', () => {
            this.workScore.resize()
          })
        },
        getStudentData(sId){
          this.$http({
            url: this.$http.adornUrl('/teacher/SearchStudent.do'),
            method: 'post',
            data:this.$http.adornData({
              'cId': localStorage.getItem('cId'),
              'sId':sId
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.submitMap = data.submitMap
              this.averScoreMap = data.averScore
              this.listInt = data.listInt
              this.listString = data.listString
              this.role = data.role
              this.initUploadWork()
              this.initAverScore()
              this.initWorkScore()
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        }
      }
    }
</script>

<style>

</style>
