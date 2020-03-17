<template>
    <div>

      <h3>活动附件</h3>
      <el-button type="primary" @click="downloadAllStudentFiles" :loading="loading">{{content}}</el-button>
      <el-button type="success" :disabled="downloadState" @click="download">下载</el-button>
      <el-table
        :data="teacherFileList"
        style="width: 100%">
        <el-table-column
          label="文件名"
          width="500">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="success"
              @click="handView(scope.row.url)">在线预览</el-button>
            <a :href="scope.row.url" download="download"> <el-button
              size="mini"
              type="warning"
            >下载</el-button></a>
          </template>
        </el-table-column>
      </el-table>

      <el-table
        :data="teacherImgFileList"
        style="width: 100%">
        <el-table-column
          label="图片"
          width="500">
          <template slot-scope="scope">
            <viewer>
              <img :src="scope.row.url" alt="" class="uploadImg">
            </viewer>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <a :href="scope.row.url" download="download"> <el-button
              size="mini"
              type="warning"
            >下载</el-button></a>
          </template>
        </el-table-column>
      </el-table>
      <h3>作业详情</h3>
      <div v-html="publishWork[0].pwContent"></div>
      <h3>学生作业情况</h3>
      <el-autocomplete
        v-model="suggestion"
        placeholder="请输入学号或姓名查询"
        :fetch-suggestions="getStudentByName"
        @select="handSelect"
      ></el-autocomplete>
      <div class="allCheck">
        <el-radio v-model="checkState" label="1" @change="getAcInfoByState('1')">待批改({{countFinishsAndNotCheckStudent}})</el-radio>
        <el-radio v-model="checkState" label="2" @change="getAcInfoByState('2')">未提交({{countNotFinishStudents}})</el-radio>
        <el-radio v-model="checkState" label="3" @change="getAcInfoByState('3')">已批改({{countFinishStudents}})</el-radio>
      </div>
      <ul>
        <li v-for="(p,sId) in students" :key="sId">
          <img :src="p.activityImgSrc" alt="" class="student-icon">
          <div class="partner-content">
            <span class="sName">{{p.sName}}</span>
            <span :class="p.twState ? 'State-able':'State-notable'">{{p.checkStringState}}</span>
            <el-button size="small" round class="sId" type="success" @click="dealWork(p.sId)">改作业</el-button>
          </div>
        </li>
      </ul>
      <el-pagination
        layout="prev, pager, next"
        :current-page="nowPage"
        class="pagination"
        @current-change="selectAc"
        :page-count="max">
      </el-pagination>
      <viewOnline ref="viewOnline"></viewOnline>

    </div>
</template>

<script>
  import viewOnline from  '../user/viewOnline'

    export default {
        data(){
          return {
            suggestion:'',
            checkState:'1',
            countNotFinishStudents: 0,
            countFinishsAndNotCheckStudent: 0,
            countFinishStudents: 0,
            students:[],
            teacherImgFileList:[],
            teacherFileList:[],
            nowPage:1,
            max:1,
            publishWork:[
              {
                pwScore: 100,
                pwName: "",
                pwEnd: "",
                activityImgSrc: "",
                pwContent: ""
              }
            ],
            advanceList:[],
            loading:false,
            content:'导出所有学生附件',
            downloadState:true,
            downloadUrl:''
          }
        },
      components:{
        viewOnline,
      },
      mounted(){
        this.getAcInfoByState(this.checkState)
      },
      methods:{
        getAcInfoByState(State){
          this.nowPage=1
          this.$http({
            url: this.$http.adornUrl('/teacher/getWorkDetails.do'),
            method: 'post',
            data: this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
              'state': State,
              'page':this.nowPage
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
               this.publishWork = data.publishWork
               this.teacherImgFileList = data.teacherFilesImages
               this.teacherFileList = data.teacherFiles
               this.students = data.students
               this.countNotFinishStudents=data.countNotFinishStudents,
               this.countFinishsAndNotCheckStudent=data.countFinishsAndNotCheckStudent,
               this.countFinishStudents=data.countFinishStudents
            }
          })
        },
        handView(url){
          this.$nextTick(() => {
            this.$refs.viewOnline.init(url)
          })
        },
        getStudentByName(name,cb){
          this.$http({
            url: this.$http.adornUrl('/teacher/fuzzySearchStudent.do'),
            method: 'post',
            data:this.$http.adornData({
              'nameOrId':name
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.advanceList=data.students
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
            url: this.$http.adornUrl('/teacher/queryWorkBySid.do'),
            method: 'post',
            data:this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
              'sId':val.sId
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.students = data.work
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        selectAc(page){
          this.nowPage=page
          this.getAcInfoByState(this.checkState)
        },
        dealWork(sId){
          sessionStorage.setItem('sId',sId)
          this.$router.push({ name: 'teacher-dealWork' })
        },
        downloadAllStudentFiles(){
          this.loading = true
          this.content = '正在导出'
          this.$http({
            url: this.$http.adornUrl('/work/downloadFileWork.do'),
            method: 'post',
            data: this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
                this.loading = false
                this.content = '导出成功，可重新导出'
                this.downloadUrl = data.Addr
                this.downloadState = false
            }
          })
        },
        download(){
          window.open(this.downloadUrl, '_blank')
        }
      }
    }
</script>

<style scoped>
  .allCheck{
    margin: 30px;
  }
  .pagination{
    width: 200px;
    margin: 0 auto;
  }
  .uploadImg{
    width: 50px;
    height: 50px;
  }
  li{
    display: inline-block;
    width: 175px;
    height: 60px;
    background: #fff;
    list-style: none;
    position: relative;
    margin-bottom: 10px;
    border:1px solid skyblue;
    border-radius: 30px 30px 30px 30px;
    margin-right: 20px;
  }

  .partner-content{
    display: inline-block;
  }
  .sName{
    color:#666;
    position: absolute;
    top: 12px;
    line-height: 15px;
  }
  .State-able{
    color:#67C23A;
    position: absolute;
    top: 12px;
    right: 11px;
    line-height: 15px;
    border: 1px solid #67C23A;
    border-radius: 4px;
    text-align: center;
    font-size: 14px;
  }
  .State-notable{
    color:#F56C6C;
    position: absolute;
    top: 12px;
    right: 11px;
    line-height: 15px;
    border: 1px solid #F56C6C;
    border-radius: 4px;
    text-align: center;
    font-size: 14px;
  }
  .sId{
    position: absolute;
    top: 30px;
    line-height: 5px;
  }
  .student-icon{
    display: inline-block;
    width: 55px;
    height: 58px;
    border-radius: 50%;
  }
</style>
