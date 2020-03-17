<template>
  <div class="mod-demo-echarts">
    <el-row :gutter="18">
      <el-col :span="12">
        <el-card>
          <div id="eFeel" class="chart-box " style="width:500px;height: 300px;margin: auto;" ></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div id="eDifficult" class="chart-box " style="width:500px;height: 300px;margin: auto;"></div>
        </el-card>
      </el-col>
      <el-col :span="24">
        <el-card>
          <div id="eSpeed" class="chart-box " style="width:500px;height: 300px;margin:20px auto;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import echarts from 'echarts'
    export default {
      data(){
        return{
          eSpeed:null,
          eFeel:null,
          eDifficult:null,
          eSpeeds:[],
          eDifficults:[],
          eFeels:[]
        }
      },
      activated () {
        // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
        if (this.eSpeed) {
          this.eSpeed.resize()
        }
        if (this.eFeel) {
          this.eFeel.resize()
        }
        if (this.eDifficult) {
          this.eDifficult.resize()
        }
      },
      mounted(){
        this.getEvaData()
      },
      methods:{
          getEvaData(){
            this.$http({
              url: this.$http.adornUrl('/teacher/selectEstimate.do'),
              method: 'post',
              data:this.$http.adornData({
                'epId': sessionStorage.getItem('epId')
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.eFeels = data.Estimate.eFeels
                this.eDifficults = data.Estimate.eDifficults
                this.eSpeeds = data.Estimate.eSpeeds
                this.initDifficult()
                this.initEfeel()
                this.initEspeed()
              }
            })
          },
          initEfeel(){
            let option = {
              title : {
                text: '课堂感受',
                subtext: '情况统计',
                x:'center'
              },
              tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
              },
              legend: {
                orient: 'vertical',
                left: 'left',
                data: ['1星','2星','3星','4星','5星']
              },
              series : [
                {
                  name: '课堂感受',
                  type: 'pie',
                  radius : '55%',
                  center: ['50%', '60%'],
                  data:this.eFeels,
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            }
            this.eFeel = echarts.init(document.getElementById('eFeel'))
            this.eFeel.setOption(option)
            window.addEventListener('resize', () => {
              this.eFeel.resize()
            })
          },
          initDifficult(){
            let option = {
              title : {
                text: '课堂难度',
                subtext: '情况统计',
                x:'center'
              },
              tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
              },
              legend: {
                orient: 'vertical',
                left: 'left',
                data: ['1星','2星','3星','4星','5星']
              },
              series : [
                {
                  name: '课堂难度',
                  type: 'pie',
                  radius : '55%',
                  center: ['50%', '60%'],
                  data:this.eDifficults,
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            }
            this.eDifficult = echarts.init(document.getElementById('eDifficult'))
            this.eDifficult.setOption(option)
            window.addEventListener('resize', () => {
              this.eDifficult.resize()
            })
          },
          initEspeed(){
            let option = {
              title : {
                text: '讲课速度',
                subtext: '情况统计',
                x:'center'
              },
              tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
              },
              legend: {
                orient: 'vertical',
                left: 'left',
                data: ['1星','2星','3星','4星','5星']
              },
              series : [
                {
                  name: '讲课速度',
                  type: 'pie',
                  radius : '55%',
                  center: ['50%', '60%'],
                  data:this.eSpeeds,
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            }
            this.eSpeed = echarts.init(document.getElementById('eSpeed'))
            this.eSpeed.setOption(option)
            window.addEventListener('resize', () => {
              this.eSpeed.resize()
            })
          }

        }
      }

</script>

<style scoped>

</style>
