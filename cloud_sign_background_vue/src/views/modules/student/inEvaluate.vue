<template>
  <div>
    <el-card class="box-card">
      <div class="text item">
        <p>讲课速度</p>
        <el-rate v-model="eSpeed" show-text></el-rate>
      </div>
    </el-card>
    <el-card class="box-card">
      <div class="text item">
        <p>课程难度</p>
        <el-rate v-model="eDifficult" show-text></el-rate>
      </div>
    </el-card>
    <el-card class="box-card">
      <div class="text item">
        <p>课堂感受</p>
        <el-rate v-model="eFeel" show-text></el-rate>
      </div>

    </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
        <i class="el-icon-edit-outline"></i><span>课堂意见</span>
      </div>
      <div class="text item">
        <script :id="eSuggest" class="ueditor-box" type="text/plain" style="width: 100%; height: 260px;"></script>
      </div>
      <el-button class="el-btn" type="success" @click="uploadEvaluate()" style="margin-top:10px;float:right">提交评价</el-button>
    </el-card>

  </div>
</template>

<script>
      import ueditor from  'ueditor'
    export default {
      data(){
        return{
          eDifficult:5,
          eSpeed:5,
          eSuggest:'eSuggest',
          eFeel:5,
          ue:null,
        }
      },
      mounted(){
        this.ue = ueditor.getEditor(this.eSuggest, {
        })

        this.ue.ready(() => {
          this.ue.setContent("在这里填写内容")
        })
      },
      destroyed() {
        this.ue.destroy();
      },
      methods:{
        uploadEvaluate(){
          this.$http({
            url:this.$http.adornUrl('/student/remarkOn.do'),
            method:'post',
            data: this.$http.adornData({
              'epId':localStorage.getItem('epId'),
              'eSpeed':this.eSpeed,
              'eDifficult':this.eDifficult,
              'eFeel':this.eFeel,
              'eSuggest':this.ue.getContent()
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
        }
      }

</script>

<style scoped>
  .box-card{
    margin-top: 20px;
  }
</style>
