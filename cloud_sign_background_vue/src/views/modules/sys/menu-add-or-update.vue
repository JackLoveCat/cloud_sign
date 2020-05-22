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
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio
            v-for="(type, index) in dataForm.typeList"
            :label="index"
            :key="index"
            >{{ type }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item
        :label="dataForm.typeList[dataForm.type] + '名称'"
        prop="name"
      >
        <el-input
          v-model="dataForm.name"
          :placeholder="dataForm.typeList[dataForm.type] + '名称'"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="上级菜单"
        prop="parentName"
        v-if="dataForm.type == 1"
      >
        <el-popover
          ref="menuListPopover"
          placement="bottom-start"
          trigger="click"
        >
          <el-tree
            :data="menuList"
            :props="menuListTreeProps"
            node-key="id"
            ref="menuListTree"
            @current-change="menuListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false"
          >
          </el-tree>
        </el-popover>
        <el-input
          v-model="dataForm.parentName"
          v-popover:menuListPopover
          :readonly="true"
          placeholder="点击选择上级菜单"
          class="menu-list__input"
        ></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type === 1" label="菜单路由" prop="url">
        <el-input v-model="dataForm.url" placeholder="菜单路由"></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 0" label="授权标识" prop="perms">
        <el-input
          v-model="dataForm.perms"
          placeholder="多个用逗号分隔, 如: user:list,user:create"
        ></el-input>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 2" label="排序号" prop="orderNum">
        <el-input-number
          v-model="dataForm.orderNum"
          controls-position="right"
          :min="0"
          label="排序号"
        ></el-input-number>
      </el-form-item>
      <el-form-item v-if="dataForm.type !== 2" label="菜单图标" prop="icon">
        <el-row>
          <el-col :span="22">
            <el-popover
              ref="iconListPopover"
              placement="bottom-start"
              trigger="click"
              popper-class="mod-menu__icon-popover"
            >
              <div class="mod-menu__icon-list">
                <el-button
                  v-for="(item, index) in iconList"
                  :key="index"
                  @click="iconActiveHandle(item)"
                  :class="{ 'is-active': item === dataForm.icon }"
                >
                  <icon-svg :name="item"></icon-svg>
                </el-button>
              </div>
            </el-popover>
            <el-input
              v-model="dataForm.icon"
              v-popover:iconListPopover
              :readonly="true"
              placeholder="菜单图标名称"
              class="icon-list__input"
            ></el-input>
          </el-col>
          <el-col :span="2" class="icon-list__tips">
            <el-tooltip placement="top" effect="light">
              <div slot="content">
                全站推荐使用SVG Sprite, 详细请参考:<a
                  href="//github.com/daxiongYang/renren-fast-vue/blob/master/src/icons/index.js"
                  target="_blank"
                  >icons/index.js</a
                >描述
              </div>
              <i class="el-icon-warning"></i>
            </el-tooltip>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { treeDataTranslate } from '@/utils'
import Icon from '@/icons'
export default {
  data () {
    var validateUrl = (rule, value, callback) => {
      if (this.dataForm.type === 1 && !/\S/.test(value)) {
        callback(new Error('菜单URL不能为空'))
      } else {
        callback()
      }
    }
    return {
      visible: false,
      dataForm: {
        id: 0,
        type: 1,
        typeList: ['目录', '菜单', '按钮'],
        name: '',
        parentId: 0,
        parentName: '',
        url: '',
        perms: '',
        orderNum: 0,
        icon: '',
        iconList: []
      },
      dataRule: {
        name: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        parentName: [
          { required: false, message: '上级菜单不能为空', trigger: 'change' }
        ],
        url: [{ validator: validateUrl, trigger: 'blur' }]
      },
      menuList: [],
      menuListTreeProps: {
        label: 'label',
        children: 'children'
      }
    }
  },
  created () {
    this.iconList = Icon.getNameList()
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.$http({
        url: this.$http.adornUrl('system/menu/treeselect'),
        method: 'get',
        params: this.$http.adornParams()
      })
        .then(res => {
          let data = res.data.data
          // console.log(res);
          this.menuList = data
          // this.menuList = treeDataTranslate(res.data.menuList, "menuId");
        })
        .then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        })
        .then(() => {
          if (!this.dataForm.id) {
            // 新增
            this.menuListTreeSetCurrentNode()
          } else {
            // 修改
            this.$http({
              url: this.$http.adornUrl(`system/menu/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(res => {
              console.log(res)

              let data = res.data.data
              this.dataForm.id = data.menuId
              console.log(data.isPage)

              if (data.isPage == 1) {
                this.dataForm.type = 1
              } else if (data.isMenu == 1) {
                this.dataForm.type = 0
              } else {
                this.dataForm.type = 2
              }
              this.dataForm.name = data.menuName
              this.dataForm.parentId = data.parentId
              this.dataForm.url = data.link
              this.dataForm.perms = data.perms
              this.dataForm.orderNum = data.orderNum
              this.dataForm.icon = data.icon
              this.menuListTreeSetCurrentNode()
            })
          }
        })
    },
    // 菜单树选中
    menuListTreeCurrentChangeHandle (data, node) {
      this.dataForm.parentId = data.id
      this.dataForm.parentName = data.label
    },
    // 菜单树设置当前选中节点
    menuListTreeSetCurrentNode () {
      this.$refs.menuListTree.setCurrentKey(this.dataForm.parentId)
      this.dataForm.parentName = (this.$refs.menuListTree.getCurrentNode() ||
        {})['label']
    },
    // 图标选中
    iconActiveHandle (iconName) {
      this.dataForm.icon = iconName
    },
    // 表单提交
    dataFormSubmit () {
      // return false;
      this.$refs['dataForm'].validate(valid => {
        let data = {
          icon: this.dataForm.icon,
          isMenu: '0',
          isPage: '0',
          link: this.dataForm.url,
          // menuId: 0,
          menuName: this.dataForm.name,
          orderNum: this.dataForm.orderNum,
          parentId: this.dataForm.parentId,
          parentName: this.dataForm.parentName,
          perms: this.dataForm.perms
          // remark: "string"
        }
        if (this.dataForm.type == 0) {
          data.isMenu = '1'
        }
        if (this.dataForm.type == 1) {
          data.isPage = '1'
        }
        if (this.dataForm.id != null) {
          data.menuId = this.dataForm.id
        }
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`system/menu/`),
            method: !this.dataForm.id ? 'post' : 'put',
            data: this.$http.adornData(data)
          }).then(({ data }) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  // window.location.reload();
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">
.mod-menu {
  .menu-list__input,
  .icon-list__input {
    > .el-input__inner {
      cursor: pointer;
    }
  }
  &__icon-popover {
    max-width: 370px;
  }
  &__icon-list {
    max-height: 180px;
    padding: 0;
    margin: -8px 0 0 -8px;
    > .el-button {
      padding: 8px;
      margin: 8px 0 0 8px;
      > span {
        display: inline-block;
        vertical-align: middle;
        width: 18px;
        height: 18px;
        font-size: 18px;
      }
    }
  }
  .icon-list__tips {
    font-size: 18px;
    text-align: center;
    color: #e6a23c;
    cursor: pointer;
  }
}
</style>
