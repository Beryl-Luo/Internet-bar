<template>
  <div class="netPlay">
    <div class="text item">
      <el-table align="center"
                :data="computerData"
                stripe>
        <el-table-column
          prop="number"
          label="机器号码"
          min-width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="所属区域"
          min-width="180">
        </el-table-column>
        <el-table-column
          prop="charging"
          label="收费（元/小时）"
          min-width="400">
        </el-table-column>
        <el-table-column
          label="是否正在被使用"
          min-width="400">
          <template slot-scope="scope">
            <span v-if="scope.row.user_id" style="color: red">{{scope.row.idcard}}</span>
            <span v-else style="color: green">暂无</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button v-if="!scope.row.user_id" size="mini"
                       @click="netPlay(scope.row)" type="text">上机</el-button>
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
  import {ComputerApi} from '../../admin/computerManage/api'
    export default {
        name: 'netPlay',
      data () {
        return {
          computerData: [],
          pagination: {
            page_size: '10', // 页面大小
            page: '1', // 当前页
            total_count: '20' // 总页数
          }
        }
      },
      mounted () {
        this.initComputer()
        this.initComputerTotal()
      },
      methods: {
        initComputer: function () {
          let array = {}
          array.method = 'getAllComputer'
          array.page = '1'
          ComputerApi.GetAllComputer(array).then(res => {
            this.computerData = JSON.parse(res.data.data)
            console.log(res.data.data)
            console.log(res.data)
          })
        },
        initComputerTotal: function () {
          let array = {}
          array.method = 'getComputerTotal'
          ComputerApi.GetComputerTotal(array).then(res => {
            this.pagination.total_count = res.data.totalCount
          })
        },
        netPlay: function (row) {
          this.$router.push({name: 'netPlayLogin', params: { id: row.id }})
        },
        changePage: function (page) {
          let array = {}
          array.method = 'getAllComputer'
          array.page = page
          ComputerApi.GetAllComputer(array).then(res => {
            this.computerData = JSON.parse(res.data.data)
          })
        }
      }
    }
</script>

<style scoped>

</style>
