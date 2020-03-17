<template>
  <div>
    <div class="header">
      <div class="header-content">
        <div class="header-box">
          <img :src="publishWork[0].activityImgSrc" alt="" class="header-img">
        </div>
        <p class="header-text"><i class="el-icon-news"></i>活动名称：{{publishWork[0].pwName}}</p>
        <p class="header-t"><i class="el-icon-time"></i>结束时间：{{publishWork[0].pwEnd}}</p>
      </div>
    </div>
    <!--作业详情-->
    <el-card class="box-card">
    <div slot="header" class="clearfix">
      <i class="el-icon-info"></i><span>活动详情</span>
    </div>
    <div class="text item" v-html="publishWork[0].pwContent">

    </div>
  </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <i class="el-icon-circle-check-outline"></i><span>成绩</span>
      </div>
      <div class="text item">
        <span>本次活动教师设置分数：</span><span class="pwScore">{{publishWork[0].pwScore}}</span><br><br>
        <span>本次活动我获得的成绩：</span><span class="wScore">{{publishWork[0].wScore}}</span>
      </div>
    </el-card>
    <!--老师提交附件-->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <i class="el-icon-download"></i><span>活动附件</span>
      </div>
      <div class="text item">
        <el-table
          :data="teacherFiles"
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
          :data="teacherFilesImges"
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
              <a :href="scope.row.url" download="download"><el-button
                size="mini"
                type="warning"
              >下载</el-button></a>
            </template>
          </el-table-column>
        </el-table>


      </div>
    </el-card>

    <!--教师评价-->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <i class="el-icon-star-off"></i><span>教师评语</span>
      </div>
      <div class="text item">
        <div v-html="publishWork[0].wRemark"></div>
      </div>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <i class="el-icon-upload"></i><span>上传文件</span>
      </div>
      <div class="text item">
        <el-alert
          title="允许上传zip,rar,7z,png,jpg,gif,txt,word,ppt,execl格式的文件 且单个大小不超过200M 如有特殊上传格式 请打包成压缩包后上传"
          type="success"
          close-text="已了解"
          show-icon>
        </el-alert>
        <el-upload
          class="upload-demo"
          :action="this.url"
          :show-file-list="true"
          :on-success="uploadSuccess"
          :headers="headers"
          multiple
          :on-error="uploadError"
          :disabled="!publishWork[0].sWState"
          :file-list="studentFiles">
          <el-button size="small" type="primary" :disabled="!publishWork[0].sWState">点击上传</el-button>
        </el-upload>

        <el-table
          :data="uoloadedList"
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
              <el-button
                size="mini"
                type="danger"
                :disabled="!publishWork[0].sWState"
                @click="handDeleteFile(scope.row.url,scope.row.sfId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>


        <el-table
          :data="studentFilesImges"
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
              <el-button
                size="mini"
                type="danger"
                :disabled="!publishWork[0].sWState"
                @click="handDeleteFile(scope.row.url,scope.row.sfId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <i class="el-icon-edit-outline"></i><span>作业文本内容</span>
      </div>
      <div class="text item">
        <el-alert
          title="提示："
          type="warning"
          :closable="false">
          <div slot-scope="description">
            <p class="el-alert__description">作业内容限制在1000字以内，且不提供图片上传，如有需求可提交在附件中。</p>
            请勿直接填写代码，如有需要请使用“代码语言”功能
          </div>
        </el-alert>
        <script :id="AcTextId" clas s="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
        </div>
          <!--疑问-->
       </el-card>

        <el-card class="box-card">
          <div slot="header" class="clearfix">
          <i class="el-icon-question"></i><span>课堂疑问</span>
        </div>
        <div class="text item">
        <script :id="AcProblem" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
      </div>
    </el-card>
    <el-button type="success" style="float: right" @click="uploadWork" :disabled="!publishWork[0].sWState">提 交 作 业</el-button>
    <viewOnline ref="viewOnline"></viewOnline>
  </div>
</template>

<script>
  import ueditor from  'ueditor'
  import viewOnline from '../user/viewOnline'
    export default {
      data(){
        return {
          publishWork:[
            {
              "pwScore": '',   //老师规定的该次的作业分数  老师设置分数区间[5,100]
              "pwName": "",  //发布作业名称
              "wRemark": "",  //教师评语
              "sWState": true,   //作业能否提交
              "activityImgSrc": "",
              "wContent": "",  //学生作答文字区
              "wProblem": "",  //学生的队该次作业的提问
              "wScore":'' ,   //老师批改的分数
              "pwContent": "",  //老师发布作业的  作业内容
              "pwEnd":""
            }
          ],
          AcInfo:'暂无数据',
          url:this.$http.adornUrl(`/work/uploadFiles?pwId=${localStorage.getItem('nowAcId')}`),
          ue:null,
          pUe:null,
          AcTextId:`AcTextId`,
          AcProblem:`AcProblem`,
          studentFiles: [],
          teacherFiles:[],
          uoloadedList:[],
          studentFilesImges:[],
          teacherFilesImges:[],
          headers:{
            token:this.$cookie.get('token')
          }
        }
      },
      components: {
        viewOnline
      },
      mounted(){
        this.getActivityInfo(),
          this.ue = ueditor.getEditor(this.AcTextId, {
          })
          this.pUe= ueditor.getEditor(this.AcProblem, {
          })
      },
      methods:{
        getActivityInfo(){
          this.$http({
            url: this.$http.adornUrl('/student/getPwDetails.do'),
            method: 'post',
            data: this.$http.adornData({
              'cId': localStorage.getItem('cId'),
              'pwId':localStorage.getItem('nowAcId')
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.uoloadedList=data.studentFiles
              this.teacherFiles=data.teacherFiles
              this.publishWork=data.publishWork
              this.studentFilesImges=data.studentFilesImages
              this.teacherFilesImges=data.teacherFilesImages
              this.ue.ready(() => {
                this.ue.setContent(this.publishWork[0].wContent)
              })
              this.pUe.ready(()=>{
                this.pUe.setContent(this.publishWork[0].wProblem)
              })
            }
          })
        },
        uploadError(){
          this.$notify({
            title: '错误提示',
            message: '上传失败请确认文件格式或者网络通畅',
            type: 'error'
          });
        },
        uploadSuccess(){
          this.$message.success("上传成功！")
          this.getActivityInfo()
        },
        handView(url){
          this.$nextTick(() => {
            this.$refs.viewOnline.init(url)
          })
        },
        handDeleteFile(url,tfId){
          this.$http({
            url: this.$http.adornUrl('/work/deleteFiles'),
            method: 'post',
            data: this.$http.adornData({
              'tfAdd': url,
              'tfId':tfId
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$notify({
                title: '成功提示',
                message: '删除成功',
                type: 'success'
              });
              this.getActivityInfo()
            }
          })
        },
        uploadWork(){
          this.$http({
            url: this.$http.adornUrl('/student/workOn.do'),
            method: 'post',
            data: this.$http.adornData({
              'pwId':localStorage.getItem('nowAcId'),
              'wContext': this.ue.getContent(),
              'wProblem':this.pUe.getContent()
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$notify({
                title: '成功提示',
                message: '提交成功',
                type: 'success'
              });
              this.$router.replace({ name: 'class' })
            }else{
              this.$message.error(data.msg)
            }
          })
        }
      },
      destroyed() {
        this.ue.destroy();
        this.pUe.destroy();
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
    padding: 2px;
    left: 90px;
    top: 10px;
    font-size: 16px;
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
    border: 1px solid #17B3A3;
    border-radius: 5px;
    overflow: hidden;
    display: inline-block;
  }
  .header{
    height: 90px;
    border-bottom: 2px solid #24A4FE;
  }
  .box-card{
    margin-top: 20px;
  }
  .mod-demo-ueditor {
    position: relative;
    z-index: 510;
  > .el-alert {
    margin-bottom: 10px;
  }
  }
  li{
    list-style: none;
    display: inline-block;
    margin-right: 15px;
  }
  .pwScore{
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
  .wScore{
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
  .uploadImg{
    width: 50px;
    height: 50px;
  }
  a{
    color: #fff;
    text-decoration: none;
  }
</style>
