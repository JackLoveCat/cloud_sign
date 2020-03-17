<template>
  <div class="header">
    <div class="header-content">
      <div class="header-box">
        <img :src="classInfo.cImgSrc" alt="" class="header-img">
      </div>
      <p class="header-text"><icon-svg name="daohang"></icon-svg>{{classInfo.cName}}</p>
      <p class="header-t"><icon-svg name="tixing"></icon-svg>{{classInfo.cId}}</p>
    </div>
    <div class="nav-header">
      <el-tabs v-model="activeName" type="border-card" class="nav-header-content">
        <el-tab-pane label="成员" name="partner">
          <!--成员-->
          <span slot="label"><i class="el-icon-star-off"></i> 成员({{classInfo.stusNum}})</span>
          <teacher-partner></teacher-partner>
        </el-tab-pane>
        <el-tab-pane label="活动" name="active">
          <span slot="label"><i class="el-icon-tickets"></i> 活动({{classInfo.pubWNum}})</span>
          <teacher-activity></teacher-activity>
        </el-tab-pane>
        <el-tab-pane label="评价" name="evaluate"> <span slot="label"><i class="el-icon-edit-outline">
        </i> 评价({{classInfo.pubENum}})</span>
          <teacher-comment></teacher-comment>
        </el-tab-pane>
        <el-tab-pane label="我的" name="myInf">
          <span slot="label"><i class="el-icon-edit"></i> 我的</span>
          <teacher-myData></teacher-myData>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import teacherpartner from './partner'
  import myData from './myData'
  import activity from './activity'
  import comment from './evaluate.vue'
    export default {
        data(){
          return {
            classInfo:{
              cName:'',
              cImgSrc:'',
              cId:'',
              pubWNum:'',
              stusNum:'',
              pubENum:''
            },
            activeName:'partner'
          }
        },
      components:{
        'teacher-partner':teacherpartner,
        'teacher-myData':myData,
        'teacher-activity':activity,
        'teacher-comment':comment
      },
      mounted(){
        this.getClassInfo()
      },
      methods:{
          getClassInfo(){
            this.$http({
              url: this.$http.adornUrl('/course/details.do'),
              method: 'post',
              data: this.$http.adornData({
                'cId': localStorage.getItem('cId')
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                    this.classInfo.cName=data.cName
                    this.classInfo.cImgSrc=data.cImgSrc
                    this.classInfo.cId=data.cId
                    this.classInfo.pubWNum=data.pubWNum
                    this.classInfo.stusNum=data.stusNum
                    this.classInfo.pubENum=data.pubENum
              }
            })
          }
      }
    }
</script>

<style scoped>
.header{
  width: 100%;
}
  .header-img{
    width: 80px;
    height: 80px;
  }
  .header-text{
    position: absolute;
    left: 90px;
    top: 10px;
    font-size: 18px;
  }
  .header-t{
    position: absolute;
    left: 90px;
    top: 40px;
    font-size: 16px;
  }
  .header-content{
    position: relative;
  }
  .header-box{
    width: 80px;
    height: 80px;
    border: 1px solid #ccc;
    border-radius: 5px;
    overflow: hidden;
    display: inline-block;
  }
  .header{
    height: 90px;

  }
  .nav-header{
    margin: 10px auto;
    width: 100%;
  }
</style>
