<template>
  <el-dialog
    title="在线预览"
    :visible.sync="dialogVisible"
    width="80%"
    :fullscreen="true"
    >
    <iframe :src=this.url width='100%' height='800' frameborder='1'>
    </iframe>

    <span slot="footer" class="dialog-footer">
  </span>
  </el-dialog>
</template>

<script>
    export default {
      data(){
        return{
          dialogVisible: false,
          url:''
        }
      },
      methods:{
        init(url){
          this.url='https://view.officeapps.live.com/op/view.aspx?src='+url
          this.dialogVisible=true
          console.log(this.url)
        },
      },
    }
</script>

<style>

</style>
