<template>
  <div class="mod-menu">
    <el-form :inline="true" :model="form">
      <el-form-item>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="menuList"
      style="width: 100%"
      row-key="menuId"
      border
      lazy
      :load="load"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column
        prop="menuName"
        width="150"
        label="名称">
      </el-table-column>

      <el-table-column
        header-align="center"
        align="center"
        label="图标">
        <template slot-scope="scope">
          <icon-svg :name="scope.row.icon || ''"></icon-svg>
        </template>
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isMenu === '1'" size="small">目录</el-tag>
          <el-tag v-else-if="scope.row.isMenu === '0'" size="small" type="success">菜单</el-tag>
          <!--<el-tag v-else-if="scope.row.type === '1'" size="small" type="info">按钮</el-tag>-->
        </template>
      </el-table-column>
      <el-table-column
        prop="orderNum"
        header-align="center"
        align="center"
        label="排序号">
      </el-table-column>
      <el-table-column
        prop="link"
        header-align="center"
        align="center"
        width="150"
        label="菜单URL">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.menuId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.menuId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="refreshDataList"></add-or-update>
  </div>
</template>

<script>
  import { listMenu,treeMenu,deleteMenu} from '@/api/sys/menu'
  import AddOrUpdate from './menu-add-or-update'
  import "element-ui/lib/theme-chalk/index.css"
  import { handleTree } from '@/utils'
  import {isAuth} from '../../../utils'
  export default {
    name: 'Menu',
    components: { AddOrUpdate },
    data () {
      return {
        // 遮罩层
        loading: true,
        // 菜单表格树数据
        menuList: [],
        // 菜单树选项
        menuOptions: [],
        // 弹出层标题
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          menuName: undefined,
          visible: undefined
        },
        form: {},
        // 显示状态数据字典
        visibleOptions: [],
        addOrUpdateVisible: true
      }
    },
    created () {
      this.getList()
    },
    methods: {
      // 显示行
      showTr: function (row, index) {
        let show = (row._parent ? (row._parent._expanded && row._parent._show) : true)
        row._show = show
        return show ? '' : 'display:none;'
      },
      // 获取数据列表
      getList () {
        this.menuList = []
        this.loading = true
        //treeMenu(this.queryParams).then(res => {
        listMenu(this.queryParams).then(res => {
          this.menuList = handleTree(res.data.rows, 'menuId')
          this.loading = false
        })
      },
      load (tree, treeNode, resolve) {
        setTimeout(() => {
          resolve(tree.children)
        }, 1)
      },
      refreshDataList () {
        this.getList()
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        this.$confirm(`确定对[id=${id}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            deleteMenu (`${id}`).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {})
      },
      haveAuth (auth) {
        console.log(auth + ' ' + isAuth(auth))
        return isAuth(auth)
      }
    }
  }
</script>
