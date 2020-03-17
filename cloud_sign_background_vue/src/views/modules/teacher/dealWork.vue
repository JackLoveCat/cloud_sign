<template>
  <div>
    <div class="student_info">
      <h3>学生信息</h3>
      <img :src="publishWork[0].activityImgSrc" alt="" class="student_ico">
      <span>姓名:{{publishWork[0].sName}}</span>
      <span>学号:{{publishWork[0].sId}}</span>
    </div>
    <h3>学生上传附件</h3>
    <el-alert
      title="提示:点击图片可放大查看,允许在线预览的文件格式有:pdf,word,xls,txt,ppt"
      type="success">
    </el-alert>
    <el-table
      :data="studentFiles"
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
            :disabled="!scope.row.state"
            @click="handView(scope.row.url)">在线预览</el-button>
          <a :href="scope.row.url" download="download"> <el-button
            size="mini"
            type="warning"
          >下载</el-button></a>
        </template>
      </el-table-column>
    </el-table>

    <el-table
      :data="studentFilesImages"
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
    <h3>学生文本内容</h3>
    <div v-html="publishWork[0].wContent"></div>
    <h3>课堂疑问</h3>
    <div v-html="publishWork[0].wProblem"></div>
    <h3>评分</h3>
    <el-slider v-model="publishWork[0].wScore" :format-tooltip="formatTooltip" show-input></el-slider>
    <h3>评价学生作业</h3>

    <script :id="wRemarkId" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
    <el-button type="success" style="float:right" @click="updateDealWork">提 交</el-button>
  </div>
</template>

<script>
  import ueditor from  'ueditor'
    export default {
      data(){
        return {
          publishWork:[
            {
              sName:"",
              sId:"",
              wRemark: "",
              wContent:"",
              wProblem: "",
              wScore: 0,
              pwContent: ""
            }
          ],
          studentFilesImages:[],
          studentFiles:[],
          wRemark:null,
          wRemarkId:`wRemark`
        }
      },
      mounted(){
          this.getStudentWork()
          this.wRemark = ueditor.getEditor(this.wRemarkId, {
          })
      },
      methods:{
        getStudentWork(){
          this.$http({
            url: this.$http.adornUrl('/teacher/selectOneWorkDetail.do'),
            method: 'post',
            data:this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
              'sId':sessionStorage.getItem('sId')
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.publishWork = data.publishWork
              this.studentFiles = data.studentFiles
              this.studentFilesImages = data.studentFilesImages
              this.wRemark.ready(()=>{
                this.wRemark.setContent(this.publishWork[0].wRemark)
              })
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        formatTooltip(val){
          return val+'分'
        },
        updateDealWork(){
          this.$http({
            url: this.$http.adornUrl('/teacher/TeacherUpadte.do'),
            method: 'post',
            data:this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
              'sId':sessionStorage.getItem('sId'),
              'wScore':this.publishWork[0].wScore,
              'wRemark':this.wRemark.getContent()
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$notify({
                title: '成功提示',
                message: '提交成功',
                type: 'success'
              });
              this.$router.push({ name: 'teacher-Correction' })
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
      },

      destroyed() {
        this.wRemark.destroy();
      }
    }
</script>

<style scoped>
.student_info{
  display: inline-block;
  vertical-align: middle;

}
.student_ico{
  width: 60px;
  height: 60px;
  border-radius: 50%;

  margin-left: 13px;
  margin-right: 15px;
}
.uploadImg{
  width: 50px;
  height: 50px;
}
</style>
