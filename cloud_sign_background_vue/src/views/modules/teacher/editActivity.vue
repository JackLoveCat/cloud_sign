<template>
  <div>
    <!--编辑活动-->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>活动名称:</h3>
      </div>
      <div class="text item">
        <el-input
          type="text" v-model="publishWork[0].pwName"
          maxlength="15"
          placeholder="最大字数为15字"
          prefix-icon="el-icon-edit-outline"
          style="width: 220px;"
        ></el-input>
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>活动结束时间</h3>
      </div>
      <div class="text item">
        <el-date-picker
          v-model="publishWork[0].pwEnd"
          type="date"
          placeholder="选择日期">
        </el-date-picker>
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>是否开启活动</h3>
      </div>
      <div class="text item">
        <el-switch
          style="display: block"
          v-model="publishWork[0].pwState"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="开启"
          inactive-text="关闭">
        </el-switch>
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>活动附件</h3>
      </div>
      <div class="text item">
        <el-upload
          class="upload-demo"
          :action="this.url"
          :on-success="uploadSuccess"
          :on-error="uploadError"
          multiple
          :show-file-list="false"
          :headers="headers"
          :file-list="FileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传zip,rar,7z,png,jpg,gif,txt,word,ppt,execl格式的文件 且单个大小不超过200Mb</div>
        </el-upload>
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
                :disabled="!scope.row.state"
                @click="handView(scope.row.url)">在线预览</el-button>
              <a :href="scope.row.url" download="download"> <el-button
                size="mini"
                type="warning"
              >下载</el-button></a>
              <el-button
                size="mini"
                type="danger"

                @click="handDeleteFile(scope.row.url,scope.row.tfId)">删除</el-button>
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
              <el-button
                size="mini"
                type="danger"
                @click="handDeleteFile(scope.row.url,scope.row.tfId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>活动详情</h3>
      </div>

      <div class="text item">
        <script :id="ueId" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
        <el-button type="success" @click="uoloadAcInfo" style="float:right;margin-top:10px">提 交</el-button>
      </div>
    </el-card>
        <viewOnline ref="viewOnline"></viewOnline>
  </div>
</template>
<script>
  import ueditor from  'ueditor'
  import viewOnline from '../user/viewOnline'
    export default {
      data(){
        return {

          url:this.$http.adornUrl(`/work/uploadFiles.do?pwId=${localStorage.getItem('nowAcId')}`),
          headers:{
            token:this.$cookie.get('token')
          },
          publishWork:[{
            pwName:'',
            pwState:true,
            pwEnd:'',
            pwContent:''
          }]
          ,
          FileList:[],
          teacherImgFileList:[],
          teacherFileList:[],
          ueId:`ueId`,
          ue:null
        }
      },
      components:{
        'viewOnline':viewOnline
      },
      mounted(){
        this.getAcInfo()
        this.ue = ueditor.getEditor(this.ueId, {
        })
      },
      methods: {
        getAcInfo(){
          this.$http({
            url: this.$http.adornUrl('/teacher/getPwDetails.do'),
            method: 'post',
            data: this.$http.adornData({
              'pwId':localStorage.getItem('nowAcId'),
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
                  this.teacherFileList = data.teacherFiles
                  this.teacherImgFileList = data.teacherFilesImages
                  this.publishWork = data.publishWork
                  this.ue.ready(() => {
                    this.ue.setContent(this.publishWork[0].pwContent)
                  })
            }else{
              this.$message.error(data.msg)
            }
          })
        },
        uploadSuccess(){
          this.$message.success("上传成功！")
          this.getAcInfo()
        },
        uploadError(){
          this.$message.error("上传失败，请确认格式和大小！")
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
              this.getAcInfo()
            }
          })
        },
        uoloadAcInfo(){
          this.$http({
            url: this.$http.adornUrl('/teacher/updatepublishWork.do'),
            method: 'post',
            data: this.$http.adornData({
              'pwId': localStorage.getItem('nowAcId'),
              'pwEnd':this.publishWork[0].pwEnd,
              'pwState':this.publishWork[0].pwState,
              'pwName':this.publishWork[0].pwName,
              'pwContent':this.ue.getContent()
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$notify({
                title: '成功提示',
                message: '提交成功',
                type: 'success'
              });
              this.$router.replace({ name: 'teacher-class' })
            }
          })
        }
      },
      destroyed() {
        this.ue.destroy();
      }
    }
</script>

<style scoped>
  .box-card{
    margin-top: 20px;
    box-shadow: 5px 5px 3px #ccc;
    margin-right: 40px;
  }
  .uploadImg{
    width: 50px;
    height: 50px;
  }
</style>
