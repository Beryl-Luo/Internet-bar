<template>
  <div class="computerDisplay">
    <div style="min-height: 500px">
      <el-row :gutter="100" >
        <el-col :span="8" :offset="4">
          <label class="el-col-7 label-own">机器号</label>
          <span>{{computer.number}}</span>
        </el-col>
        <el-col :span="8" >
        <label class="el-col-7 label-own">机器区域</label>
        <span>{{computer.name}}</span>
      </el-col>
      </el-row>
      <el-row :gutter="100" >
        <el-col :span="8" :offset="4">
          <label class="el-col-7 label-own">收费(元/小时)</label>
          <span>{{computer.charging}}</span>
        </el-col>
        <el-col :span="8">
          <label class="el-col-7 label-own">上网开始时间</label>
          <span>{{new Date(computer.start_at).toUTCString()}}</span>
        </el-col>
      </el-row>
      <el-row :gutter="100" >
        <el-col :span="8" :offset="4">
          <label class="el-col-7 label-own">上网人员</label>
          <span>{{computer.idcard}}</span>
          <el-button type="success" v-if="computer.idcard" @click="checkout">结账下机</el-button>
        </el-col>
      </el-row>
    </div>
    <el-button @click="cancel()">取消</el-button>
  </div>
</template>

<script>
  import {ComputerApi} from './api'
  export default {
    name: 'computerDisplay',
    data () {
      return {
        way: '',
        computer: {
          id: this.$route.params.id,
          number: '',
          name: '',
          charging: '',
          idcard: '',
          start_at: '',
          user_id: ''
        }
      }
    },
    mounted () {
      this.getComputerById()
    },
    methods: {
      cancel: function () {
        this.$router.push({name: 'computerList'})
      },
      getComputerById: function () {
        let array = {}
        array.method = 'getComputerById'
        array.id = this.computer.id
        ComputerApi.GetComputerById(array).then(res => {
          this.computer = JSON.parse(res.data.data)[0]
        })
      },
      checkout: function () {
        let array = {}
        array.method = 'checkout'
        array.computerId = this.$route.params.id
        array.userId = this.computer.user_id
        ComputerApi.Checkout(array).then(res => {
          if (res.data.status) {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
            this.$router.push({name: 'computerList'})
          } else {
            this.$message.error(res.data.message)
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .computerDisplay{
    .label-own {
      font-size: 15px;
      font-weight:600;
      text-align: right;
    }
    span{
      font-size: 14px
    }
    .el-col{
      height: 50px;
    }
  }

</style>
