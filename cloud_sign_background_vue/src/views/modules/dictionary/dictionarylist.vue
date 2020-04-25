<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never" style="margin-top: 15px">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right;margin-left:10px;"
          v-on:click="$router.back(-1)"
          size="small">
          返回
        </el-button>
        <el-button
          style="float: right"
          type="primary"
          v-on:click="getList"
          size="small">
          查询结果
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="输入搜索：">
            <el-input style="width: 203px" v-model="listQueryname" placeholder="名称/关键字"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>


    <div class="operate-container" shadow="never" style="margin-top: 15px">
      <div slot="header" class="clearfix">
        <el-button size="mini" class="btn-add" @click="handleAdd" >添加</el-button>
        <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0">批量删除</el-button>
      </div>
      <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;margin-top: 15px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="ID">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="名称">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="value">
          <template slot-scope="scope">
            <span>{{ scope.row.value }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="排序">
          <template slot-scope="scope">
            <span>{{ scope.row.sort }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />



    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="ID" prop="id">
          <el-input v-model="addForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="值" prop="值">
          <el-input v-model="addForm.value" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="排序">
          <el-input v-model="addForm.sort" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible=false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" >提交</el-button>
      </div>
    </el-dialog>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label="ID" prop="id">
          <el-input v-model="update.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="update.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="值" prop="值">
          <el-input v-model="update.value" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="排序">
          <el-input v-model="update.sort" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="_editSubmit" >提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  var _index
import { fetchList, deleteEntity, removeEntity, addEntity, updateEntity} from '@/api/dictionary'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  const statusMap = {
    '0': '禁用',
    '1': '启用'
  }

  export default {
    name: 'bannerAdvList',
    components: { Pagination },
    filters: {
      statusFilter (status) {
        return statusMap[status]
      }
    },
    data () {
      return {
        advStatusMap: [
          {
            value: '0',
            label: '禁用'
          }, {
            value: '1',
            label: '启用'
          }
        ],
        multipleSelection: [], // 当前选择
        list: [
          {
            id: '01',
            name: '张三',
            value: 0,
            sort: 'No.1'
          }
        ], // 当前list
        total: 0,
        listLoading: false,
        listQuery: { // 查询分页
          name: '',
          pageNo: 1,
          pageSize: 10
        },
        listQueryname: '',
        addFormVisible: false, // 新增界面是否显示
        addLoading: false,
        addFormRules: {
          name: [
            { required: true, message: '请输入名称', trigger: 'blur' }
          ]
        },
        // 新增界面数据
        addForm: {
          id: '',
          name: '',
          value: '',
          solt: ''
        },
        editFormVisible: false, // 编辑界面是否显示
        editLoading: false,
        editFormRules: {
          name: [
            { required: true, message: '请输入名称', trigger: 'blur' }
          ]
        },
        // 编辑界面数据
        editForm: {
          id: 0,
          name: ''
        },
        // 更新的数据
        update: []

      }
    },
    computed: {
      // 用computed监听tables，使用filter过滤数组中包含被搜索的关键词，将匹配到含有关键词的数据筛选出来
      // 返回给tables，此时tables就是一个新的数组
      // toLowerCase是转换小写的，因为有大小写的话some方法会失效
      // 模糊搜索
      tables () {
        const listQueryname = this.listQueryname
        if (listQueryname) {
          return this.list.filter(data => {
            return Object.keys(data).some(key => {
              return String(data[key]).toLowerCase().indexOf(listQueryname) > -1
            })
          })
        }
        return this.list
      }
    },
    methods: {
      // 当点击查询按钮的时候，将tables数组赋值给el-table的data中绑定的list，这样页面渲染的就是通过搜索筛选出来的数据了
      getList () {
        this.list = this.tables
      },
      // 全选
      handleSelectionChange (val) {
        this.multipleSelection = val
      },
      handleAdd: function () {
        // 当点击添加按钮时，addFormVisible为true,即显示弹框
        this.addFormVisible = true
      },
      addSubmit () {
        // 点添加弹框中的提交之后执行的时间
        // this.addForm是添加框中绑定的对象，输入的内容都会存入这个对象
        // 点击提交的时候push到list数组，就可完成数据添加
        var newform = {}
        newform.id = this.addForm.id
        newform.name = this.addForm.name
        newform.value = this.addForm.value
        newform.sort = this.addForm.sort
        this.list.push(newform)
        this.addFormVisible = false
      },
      handleDel (index, row) {
        // 删除的逻辑：this.$confirm是element的提示信息功能
        // 确认删的时候this.list.splice(index, 1)使用splice方法将list数组的第index条移出数组
        // index是删除按钮中@click="handleDel(scope.$index, scope.row)"传入的参数，代表所删除对应的 行
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.list.splice(index, 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch((err) => {
          this.$message({
            type: 'error',
            message: err
          })
        })
      },
      // 编辑逻辑
      // 创建一个全局变量，
      // 在script开始位置，
      // 在函数中将变量赋值给editData，代表的是list数组中的下下标
      // 在修改编辑框中input绑定的数据是update
      // 所以只要把修改后的数据赋值给表格中的数据list，即可完成更新
      handleEdit (index, row) {
        this.editFormVisible = true
        this.update = Object.assign({}, row)
        _index = index
      },
      // 编辑
      _editSubmit () {
        var editData = _index
        this.list[editData].id = this.update.id
        this.list[editData].name = this.update.name
        this.list[editData].value = this.update.value
        this.list[editData].solt = this.update.solt
        this.editFormVisible = false
      },
      delall () {
        // 批量删：删除数组中0-数组长度的项，即全部
        this.list.splice(0, this.list.length)
      }

    }
  }
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }
  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
