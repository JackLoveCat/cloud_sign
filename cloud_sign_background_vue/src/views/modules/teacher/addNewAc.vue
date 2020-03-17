<template>
  <el-dialog
    title="发布活动"
    :visible.sync="dialogVisible"
    width="40%"
    :before-close="handeClose"
    top="15px"
  >
    <h4>是否开启活动</h4>
    <el-switch
      v-model="pwState"
      inactive-text="关闭活动"
      active-text="开启活动"
      >
    </el-switch>
    <h4>活动名称</h4>
    <el-input v-model="pwName" placeholder="请输入活动名称，最多不超过十五个字" maxlength="15" prefix-icon="el-icon-edit"
     minlength="1"
    ></el-input>
    <h4>活动结束时间</h4>
    <el-date-picker
      v-model="pwEnd"
      type="date"
      placeholder="选择活动结束时间">
    </el-date-picker>
    <h4>上传附件</h4>
    <el-upload
      class="upload-demo"
      :action="this.url"
      :show-file-list="true"
      :on-success="uploadSuccess"
      :headers="headers"
      multiple
      :on-error="uploadError"
      :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
    <h4>活动详情</h4>
      <script :id="pwContentId" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
    <el-button type="success" icon="el-icon-upload2" @click="updatePublishWork" style="margin-top:10px;">发布活动</el-button>
  </el-dialog>

</template>

<script>
  import ueditor from  'ueditor'
    export default {
      data(){
        return {
          dialogVisible:false,
          pwEnd:'',
          pwState:true,
          pwContentId:`pwContentId`,
          pwName:'',
          pwContent:null,
          url:this.$http.adornUrl(`/work/uploadFiles?pwId=${localStorage.getItem('nowAcId')}`),
          headers:{
            token:this.$cookie.get('token')
          },
          fileList:[]
        }
      },

      methods:{
        init(){
          this.dialogVisible = true
          this.$http({
            url: this.$http.adornUrl('/teacher/publishWork.do'),
            method: 'post',
            data:this.$http.adornData({
              'cId': localStorage.getItem('cId'),
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              sessionStorage.setItem('newPwId',data.pwId)
              this.pwContent = ueditor.getEditor(this.pwContentId, {
              })
              this.pwContent.ready(() => {
                this.pwContent.setContent("")
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
        handeClose(done){
              this.pwEnd='',
              this.pwState=true,
              this.pwContentId=`pwContentId`,
              this.pwName='',
              this.pwContent.setContent("");
              this.destroyed()
              done()

        },
        destroyed(){
          this.pwContent.destroy()
        },
        updatePublishWork(){
          this.$http({
            url: this.$http.adornUrl('/teacher/updatepublishWork.do'),
            method: 'post',
            data:this.$http.adornData({
              'pwId': sessionStorage.getItem('newPwId'),
              'pwEnd': this.pwEnd,
              'pwName': this.pwName,
              'pwState': this.pwState,
              'pwContent': this.pwContent.getContent()
            })
          }).then(({data}) => {
            if (data && data.status === 200) {
              this.$notify({
                title: '成功提示',
                message: '发布成功',
                type: 'success'
              });
             this.dialogVisible = false
             this.$emit("listenAddEvent",true)
            }
            else {
              this.$message({
                message:data.msg,
                type:'error'
              })
            }
          })
        },
        uploadSuccess(){
          this.$message({
            message:"上传成功",
            type:'success'
          })
        },
        uploadError(){
          this.$message({
            message:"上传失败",
            type:'error'
          })
        }
      }
    }
</script>

<style>

</style>
