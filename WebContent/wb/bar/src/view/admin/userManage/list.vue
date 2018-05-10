<template>
  <div class="userList">
    <div class="text item">
      <el-button style="float: left;" type="primary" @click="add()">新增</el-button>
      <el-table align="center"
                :data="userData"
                stripe>
        <el-table-column
          prop="idname"
          label="姓名"
          min-width="180">
        </el-table-column>
        <el-table-column
          prop="idcard"
          label="身份证号"
          min-width="300">
        </el-table-column>
        <el-table-column
          prop="balance"
          label="余额(元)"
          min-width="180">
        </el-table-column>
        <el-table-column
          label="会员等级"
          min-width="200">
          <template slot-scope="scope">
            <span style="color: green">{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="是否正在上网"
          min-width="200">
          <template slot-scope="scope">
            <span v-if="scope.row.status==1" style="color: red">{{scope.row.status}}</span>
            <span v-else style="color: green">否</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini"
                       @click="edit(scope.row)" type="text">编辑</el-button>
            <el-button size="mini"
                       @click="getRow(scope.row)" type="text">查看</el-button>
            <el-button size="mini"
                       @click="deleteUser(scope.row)" type="text">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="page" style="margin-top:20px"
                     layout="prev, pager, next"
                     background
                     @current-change="changePage"
                     prev-text="上一页"
                     next-text="下一页"
                     :page-size="parseInt(pagination.page_size)"
                     :current-page="parseInt(pagination.page)"
                     :total="parseInt(pagination.total_count)">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {UserApi} from './api'
    export default {
      name: 'userList',
      data () {
        return {
          userData: [
            {

            }
          ],
          pagination: {
            page_size: '10', // 页面大小
            page: '1', // 当前页
            total_count: '20' // 总页数
          }
        }
      },
      mounted () {
        this.initUser()
        this.initUserTotal()
      },
      methods: {
        add: function () {
          this.$router.push({name: 'userAddForm'})
        },
        initUser: function () {
          let array = {}
          array.method = 'getAllUser'
          array.page = '1'
          UserApi.GetAllUser(array).then(res => {
            this.userData = JSON.parse(res.data.data)
          })
        },
        initUserTotal: function () {
          let array = {}
          array.method = 'getUserTotal'
          UserApi.GetUserTotal(array).then(res => {
            this.pagination.total_count = res.data.totalCount
          })
        },
        edit: function (row) {
          this.$router.push({name: 'userEditForm', params: { id: row.id }})
        },
        getRow: function (row) {
          this.$router.push({name: 'userDisplay', params: { id: row.id }})
        },
        deleteUser: function (row) {
          let array = {}
          array.method = 'deleteUser'
          array.id = row.id
          UserApi.DeleteUser(array).then(res => {
            if (res.data.status) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.initUser()
              this.initUserTotal()
            } else {
              this.$message.error(res.data.message)
            }
          })
        },
        changePage: function (page) {
          let array = {}
          array.method = 'getAllUser'
          array.page = page
          UserApi.GetAllUser(array).then(res => {
            this.userData = JSON.parse(res.data.data)
          })
        }
      }

    }
</script>

<style scoped>

</style>
