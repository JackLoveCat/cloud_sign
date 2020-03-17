<template>
  <div>

      <el-autocomplete
        placeholder="请输入活动名称搜索"
        suffix-icon="el-icon-search"
        v-model="activityName" class="searchInput"
        :fetch-suggestions="getActivityByName"
        :trigger-on-focus="false"
        @select="handSelect"
      >
      </el-autocomplete>

    <div class="allCheck">
      <el-radio v-model="checkState" label="1" @change="getActivityByState('1')">所有活动({{countall}})</el-radio>
      <el-radio v-model="checkState" label="2" @change="getActivityByState('2')">进行中({{countprocess}})</el-radio>
      <el-radio v-model="checkState" label="3" @change="getActivityByState('3')">已结束({{countover}})</el-radio>
    </div>
    <ul>
      <li v-for="(p,index) in activityList" :key="index">
        <img :src="p.activityImgSrc" alt="活动图片" class="activityImg">
        <div class="activityContent">
            <span :class="p.pwBooleanState ? 'activityState-able' : 'activityState-notAble'">{{p.pwStringState}}</span>
            <span :class="p.wBooleanState?'joinState-able':'joinState-notAble'">{{p.wStringState}}</span>
            <span class="myScore">得分:{{p.wScore}}</span>
            <span class="total">分值:{{p.pwScore}}</span>
            <span class="finishedNum">参与人数:{{p.finishedNum}}</span>
            <span>{{p.pwName}}</span>
            <p>结束时间：{{p.pwEnd}}</p>
        </div>
        <el-button class="ac-btn" type="success" @click="inActivity(p.pwId)">进入活动</el-button>
      </li>
    </ul>
    <el-pagination
      layout="prev, pager, next"
      :page-count="max" class="pagination"
      @current-change="selectAc"
      :current-page="nowPage"
    >
    </el-pagination>
  </div>
</template>

<script>
    export default {
      data(){
        return{
          activityName:'',
          checkState:'1',
          activityList:[],
          advanceList:[],
          nowPage:1,
          countall:'',
          countprocess:'',
          countover:'',
          max:1,
        }
      },
      mounted(){
        this.getActivityByState(this.checkState)
      },
      methods:{
        selectAc(page){
          this.nowPage=page
          this.getActivityByState(this.checkState)
        },
        getActivityByState(State){
          this.nowPage=1
          this.$http({
            url: this.$http.adornUrl('/student/selectPw.dotime'),
            method: 'post',
            data:this.$http.adornData({
              'page':this.nowPage,
              'cId': localStorage.getItem('cId'),
              'state':State
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.activityList=data.publishWorks
              this.max=data.max
              this.countall=data.countall
              this.countprocess=data. countprocess
              this.countover=data.countover
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        getActivityByName(name,cb){
          this.$http({
            url: this.$http.adornUrl('/student/fuzzySearchWork.do'),
            method: 'post',
            data:this.$http.adornData({
              'cId': localStorage.getItem('cId'),
              'pwName':name
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.advanceList=data.fuzzySearchWorks
              cb(this.advanceList)
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        handSelect(val){
          this.$http({
            url: this.$http.adornUrl('/student/SearchPwByPwName.dotime'),
            method: 'post',
            data:this.$http.adornData({
              'cId': localStorage.getItem('cId'),
              'pwName':val.value
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.activityList=data.fuzzySearchWorks
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        inActivity(pwId){
          localStorage.setItem('nowAcId',pwId)
          this.$router.push({name:'updateActivity'})
        }
      }
    }
</script>

<style scoped>
  .searchInput{
    width: 300px;
  }
  li{
    height: 90px;
    list-style: none;
    border-bottom: 1px solid #e1e1e1;
  }
  li:hover{
    border: 1px solid #0BD;
  }
.allCheck{
  margin: 30px;
}
  .activityState-able{
    width: 50px;
    height: 20px;
    border: 1px solid #0BD;
    color: #0BD;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .activityState-notAble{
    width: 50px;
    height: 20px;
    border: 1px solid #909399;
    color: #909399;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .joinState-able{
    width: 50px;
    height: 20px;
    border: 1px solid #67C23A;
    color: #67C23A;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .joinState-notAble{
    width: 50px;
    height: 20px;
    border: 1px solid #E6A23C;
    color: #E6A23C;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .activityImg{
    width: 60px;
    height: 60px;
    border-radius: 8px;
    vertical-align: middle;
    margin-left: 13px;
    margin-right: 15px;
  }
  .activityContent{
    display: inline-block;
    vertical-align: middle;
    margin-top: 22px;
  }
  .pagination{
    width: 200px;
    margin: 0 auto;
  }
  .ac-btn{
    float: right;
    margin-top: 30px;
    margin-right: 30px;
  }
  .total{
    width: 60px;
    height: 20px;
    border: 1px solid #F56C6C;
    color: #F56C6C;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .myScore{
    width: 60px;
    height: 20px;
    border: 1px solid #409EFF;
    color: #409EFF;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
  .finishedNum{
    width: 85px;
    height: 20px;
    border: 1px solid #81C0C0;
    color: #81C0C0;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
  }
</style>
