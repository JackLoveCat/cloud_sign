<template>
  <div>
    <div class="noData" v-if="Viable">
      <img src="~@/assets/img/pj.jpg" alt="" >
    </div>
    <ul>
      <li v-for="(p,index) in publishEstimates" :key="index" >
        <img :src="p.activityImgSrc" alt="" class="activityImg">
        <div class="activityContent">
          <span class="elName">标题</span>{{p.cName}}
          <p class="eTime">开始时间:{{p.epStartTime}}</p>
        </div>
        <el-button class="el-btn" type="success" @click="inEvaluate(p.epId)">开始评价</el-button>
      </li>
    </ul>
    <el-pagination
      layout="prev, pager, next"
      @current-change="selectEl"
      :page-count="max" class="pagination">
    </el-pagination>
  </div>
</template>
<script>
    export default {
        data(){
          return {
              Viable:false,
              publishEstimates:[],
              max:1,
          }
        },
      mounted(){
             this.getEvaluateList()
      },
      methods:{
          getEvaluateList(page=page?page:1){
            this.$http({
              url: this.$http.adornUrl('/student/selectPE.do'),
              method: 'post',
              data: this.$http.adornData({
                'cId': localStorage.getItem('cId'),
                'page':page
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                if(data.publishEstimates.length==0){
                      this.Viable=true
                }else{
                  this.publishEstimates=data.publishEstimates
                  this.max=data.max
                }
              }else{
                this.$message({
                  message:data.msg,
                  type:'error'
                })
              }
            })
          },
        selectEl(page){
            this.getEvaluateList(page)
        },
        inEvaluate(epId){
            localStorage.setItem('epId',epId)
            this.$router.push({name:'inEvaluate'})
        }
      }
    }
</script>

<style scoped>
.noData{
  width: 100%;
  text-align: center;
  margin:0 auto;
}
li{
  height: 90px;
  list-style: none;
  border-bottom: 1px solid #e1e1e1;
}
li:hover{
  border: 1px solid #0BD;
}
.pagination{
  width: 200px;
  margin: 0 auto;
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
  .eTime{
    width: 150px;
    height: 20px;
    border: 1px solid #F5C252;
    color: #F5C252;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    margin-right: 5px;
  }
  .elName{
    width: 50px;
    height: 20px;
    border: 1px solid #0BD;
    color: #0BD;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    display: inline-block;
    margin-right: 5px;
  }
  .el-btn{
    float: right;
    margin-top: 30px;
    margin-right: 30px;
  }
</style>
