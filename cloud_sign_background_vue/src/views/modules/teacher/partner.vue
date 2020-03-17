<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(255, 255, 255, 0.8)"
  >
    <ul>
        <li v-for="(p,index) in studentList" :key="index" @click="showStudentInfo(p.sId)">
          <img :src="p.sImgSrc" alt="" class="student-icon">
          <div class="partner-content">
            <p class="sName">{{p.sName}}&nbsp;排名:{{p.rank}}</p>
            <p class="sumScore">总分:{{p.sumScore}}</p>
            <p class="sId">{{p.sId}}</p>
          </div>
        </li>
    </ul>
    <el-pagination

      layout="prev, pager, next"
      :page-count="totalPage" class="paging"
      @current-change="handChange"
    >
    </el-pagination>

    <showStudentInfo ref="showStudentInfo"></showStudentInfo>
  </div>

</template>
<script>
  import showStudentInfo from './showStudentInfo'
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
      components:{
        showStudentInfo
      },
      methods:{
          getPartner(page=page?page:1){
            this.$http({
              url: this.$http.adornUrl('/teacher/SearchStudents.do'),
              method: 'post',
              data:this.$http.adornData({
                'page':page,
                'cId': localStorage.getItem('cId')
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.studentList=data.students
                this.totalPage=data.max
                this.loading=false
              }
            })
          },
        handChange(page){
            this.getPartner(page)
        },
        showStudentInfo(sId){
          this.$nextTick(() => {
            this.$refs.showStudentInfo.init(sId)
          })
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
  border:1px solid skyblue;
  border-radius: 30px 30px 30px 30px;
  margin-right: 20px;
  cursor: pointer;
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
  top: 0px;
  line-height: 5px;
}
.sId{
  position: absolute;
  top: 33px;
  color: #888;
  line-height: 5px;
}
  .paging{
    width: 200px;
    margin: auto;
    margin-top: 100px;
  }
  .sumScore{
    position: absolute;
    top: 11px;
    color: #888;
  }
</style>
