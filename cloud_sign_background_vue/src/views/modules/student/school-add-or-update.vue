<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
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
      <el-form-item label="学校名称" prop="universityName">
        <el-input
          v-model="dataForm.universityName"
          placeholder="学校名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="院系名称" prop="academyName">
        <el-input
          v-model="dataForm.academyName"
          placeholder="院系名称"
        ></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { isEmail, isMobile } from "@/utils/validate";
export default {
  data() {
    var validatePassword = (rule, value, callback) => {
      if (!this.dataForm.id && !/\S/.test(value)) {
        callback(new Error("密码不能为空"));
      } else {
        callback();
      }
    };
    var validateComfirmPassword = (rule, value, callback) => {
      if (!this.dataForm.id && !/\S/.test(value)) {
        callback(new Error("确认密码不能为空"));
      } else if (this.dataForm.password !== value) {
        callback(new Error("确认密码与密码输入不一致"));
      } else {
        callback();
      }
    };
    var validateEmail = (rule, value, callback) => {
      if (!isEmail(value)) {
        callback(new Error("邮箱格式错误"));
      } else {
        callback();
      }
    };
    var validateMobile = (rule, value, callback) => {
      if (!isMobile(value)) {
        callback(new Error("手机号格式错误"));
      } else {
        callback();
      }
    };
    return {
      visible: false,
      roleList: [],
      dataForm: {
        id: null,
        universityName: "",
        academyName: ""
        // classNum: ""
      },
      dataRule: {
        universityName: [
          { required: true, message: "学校名称不能为空", trigger: "blur" }
        ],
        academyName: [
          { required: true, message: "院系名称不能为空", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    init(row) {
      let id;
      if (row) {
        id = row.uniacadaId;
      }
      console.log(row);

      this.dataForm.id = id || null;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id != null) {
          this.dataForm.universityName = row.universityName;
          this.dataForm.academyName = row.academyName;
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      var methodtype = this.dataForm.id ? "put" : "post";
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl("system/uniacada"),
            method: methodtype,
            data: this.$http.adornData({
              uniacadaId: this.dataForm.id || null,
              universityName: this.dataForm.universityName,
              academyName: this.dataForm.academyName
              // classNum: this.dataForm.classNum
            })
          }).then(({ data }) => {
            if (data && data.code === 200) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
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
