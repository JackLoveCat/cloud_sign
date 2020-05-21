<template>
  <el-dialog
    :title="dataForm.courseId == null ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="id" prop="courseId" hidden>
        <el-input v-model="dataForm.courseId" placeholder="id"></el-input>
      </el-form-item>
      <el-form-item label="课程名称" prop="courseName">
        <el-input
          v-model="dataForm.courseName"
          placeholder="课程名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="老师名称" prop="teacherName">
        <el-input
          v-model="dataForm.teacherName"
          placeholder="老师名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="院系名称" prop="uniacadaId">
        <el-select v-model="dataForm.uniacadaId">
          <el-option
            :label="item.label"
            :value="item.value"
            v-for="item in schoolList"
            :key="item.label"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { treeDataTranslate } from "@/utils";
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        courseId: null,
        courseName: "",
        teacherName: "",
        uniacadaId: 0,
        remark: ""
      },
      dataRule: {
        roleName: [
          { required: true, message: "角色名称不能为空", trigger: "blur" }
        ]
      },
      tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
    };
  },
  props: ["schoolList", "nowSchool"],
  methods: {
    init(row) {
      console.log(row);

      this.dataForm.courseId = row.courseId == undefined ? null : row.courseId;
      this.visible = true;
      this.$nextTick(() => {
        if (this.$refs["dataForm"]) {
          this.$refs["dataForm"].clearValidate();
        }
        if (
          this.dataForm.courseId != undefined &&
          this.dataForm.courseId != null
        ) {
          this.dataForm.courseName = row.courseName;
          this.dataForm.teacherName = row.teacherName;
          this.dataForm.uniacadaId = this.nowSchool;
          this.dataForm.remark = row.remark;
        } else {
          this.dataForm = {
            courseId: null,
            courseName: "",
            teacherName: "",
            uniacadaId: 0,
            remark: ""
          };
          this.dataForm.uniacadaId = this.schoolList[0].value;
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          let url =
            this.dataForm.courseId == null ? "cla/course/create" : "cla/course";
          let method = this.dataForm.courseId == null ? "post" : "put";
          this.$http({
            url: this.$http.adornUrl(url),
            method: method,
            data: this.$http.adornData(this.dataForm)
          }).then(({ data }) => {
            if (data && data.code === 200) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList", this.nowSchool);
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
/deep/ .el-select {
  width: 100%;
}
</style>
