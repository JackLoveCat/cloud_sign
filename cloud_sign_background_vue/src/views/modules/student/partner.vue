<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(255, 255, 255, 0.8)"
  >
    <ul>
        <li v-for="(p,index) in studentList" :key="index">
          <img :src="p.sImgSrc" alt="" class="student-icon">
          <div class="partner-content">
            <p class="sName">{{p.sName}}</p>
            <p class="sId">{{p.sId}}</p>
          </div>
        </li>
    </ul>
    <el-pagination
      background
      layout="prev, pager, next"
      :page-count="totalPage" class="paging"
      @current-change="handChange"
    >
    </el-pagination>
  </div>

</template>
<script>
    export default {
        data(){
          return{
            loading:true,
            studentList:[],
            totalPage:1
          }
        },
        mounted(){
          this.getPartner()
        },
      methods:{
          getPartner(page=page?page:1){
            this.$http({
              url: this.$http.adornUrl('/course/queryStudents.do'),
              method: 'post',
              data:this.$http.adornData({
                'page':page,
                'cId': localStorage.getItem('cId')
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.studentList=data.students
                this.totalPage=data.pageNum
                this.loading=false
              }
            })
          },
        handChange(page){
            this.getPartner(page)
        }
      }
    }
</script>

<style scoped>
  ul{
    width: 100%;
    height: 100%;
  }
li{
  display: inline-block;
  width: 170px;
  height: 60px;
  background: #fff;
  list-style: none;
  position: relative;
  margin-bottom: 10px;
  border:1px solid #E8CCA5;
  border-radius: 30px 30px 30px 30px;
  margin-right: 20px;
}
.student-icon{
  display: inline-block;
  width: 55px;
  height: 58px;
  border-radius: 50%;
}
.partner-content{
  display: inline-block;
}
.sName{
  color:#666;
  position: absolute;
  top: 0;
}
.sId{
  position: absolute;
  top: 20px;
  color: #888;
}
  .paging{
    width: 200px;
    margin: auto;
    margin-top: 100px;
  }
</style>
