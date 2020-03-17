<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>头像上传</h3>
      </div>
      <div class="text item">
        <el-upload
          class="upload-demo"
          :action="url"
          :file-list="fileList"
          :headers="headers"
          :on-success="onsuccess"
          list-type="picture">
          <el-button size="small" type="primary">更换头像</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2Mb</div>
        </el-upload>
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h3>个人属性设置</h3>
      </div>
      <div class="text item">
        <div class="form-item">
          <el-input placeholder="请输入内容" v-model="role.name" :disabled="true">
            <template slot="prepend">姓名</template>
          </el-input>
        </div>
          <div class="form-item">
            <el-input placeholder="请输入学号" v-model="role.userName" :disabled="true">
              <template slot="prepend">学号</template>
            </el-input>
          </div>
        <div class="form-item">
          <el-input placeholder="请输入手机号" v-model="role.userPhone" >
            <template slot="prepend">手机号</template>
          </el-input>
        </div>
        <div class="form-item">
          <el-input placeholder="请输入邮箱" v-model="role.userEmail" >
            <template slot="prepend">邮箱</template>
          </el-input>
        </div>
      </div>
      <el-button type="success" class="upbtn" @click="updateUserInfo">提交</el-button>
    </el-card>
  </div>

</template>

<script>
    export default {
        data(){
          return {
            headers:{
              token:this.$cookie.get('token')
            },
            url:this.$http.adornUrl('/role/updateAvatar.do'),
            fileList:[],
            role:{
              name:'',
              userName:'',
              userPhone:'',
              userEmail:'',
            }
          }
        },
      mounted(){
          this.getUserInfo()
      },
      computed:{
        userImgSrc:{
          get() {return this.$store.state.user.userImgSrc},
          set(val){this.$store.commit('user/updateImgSrc', val)}
        }
      },
      methods:{
          getUserInfo(){
            this.$http({
              url: this.$http.adornUrl('/role/getRole.do'),
              method: 'post',
            }).then(({data}) => {
              if (data && data.status === 200) {
                  this.role = data.role

              }
            })
          },
          updateUserInfo(){
            this.$http({
              url: this.$http.adornUrl('/role/updateRoleInfo.do'),
              method: 'post',
              data: this.$http.adornData({
                'email': this.role.userEmail,
                'phone': this.role.userPhone
              })
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.$notify({
                  title: '成功提示',
                  message: '修改成功',
                  type: 'success'
                });
                this.$router.replace({ name: 'home' })
              }
            })
          },
        onsuccess(response, file, fileList){
          this.$message.success("上传成功！")
          this.userImgSrc = file.url
        }
      }
    }
</script>

<style scoped>
.box-card{
  margin-top: 20px;
  box-shadow: 5px 5px 3px #ccc;
}
.box-card:hover{
  border: 1px solid #0BB2D4;
}
  .form-item{
    width: 300px;
    display: inline-block;
    margin: 10px 30px;
  }
  .upbtn{
    margin-left: 30px;
  }
</style>
