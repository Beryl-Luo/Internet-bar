<template>
  <div class="userDisplay">
    <div style="min-height: 500px">
      <el-row :gutter="100" >
        <el-col :span="8" :offset="4">
          <label class="el-col-7 label-own">姓名</label>
          <span>{{user.idname}}</span>
        </el-col>
        <el-col :span="8" >
          <label class="el-col-7 label-own">身份证号</label>
          <span>{{user.idcard}}</span>
        </el-col>
      </el-row>
      <el-row :gutter="100">
        <el-col :span="8"  :offset="4">
          <label class="el-col-7 label-own">会员等级</label>
          <span>{{user.name}}</span>
        </el-col>
        <el-col :span="8" >
          <label class="el-col-7 label-own">余额(元)</label>
          <span>{{user.balance}}</span>
        </el-col>
      </el-row>
      <el-row :gutter="100" >
        <el-col :span="8" :offset="4">
          <label class="el-col-7 label-own">密码</label>
          <span>{{user.password}}</span>
        </el-col>
        <el-col :span="8">
          <label class="el-col-7 label-own">上网开始时间</label>
          <span v-if="user.status == 1">{{new Date(user.start_at).toUTCString()}}</span>
        </el-col>
      </el-row>
    </div>
    <el-button @click="cancel()">取消</el-button>
  </div>
</template>

<script>
  import {UserApi} from './api'
    export default {
        name: 'userDisplay',
      data () {
        return {
          way: '',
          user: {
            id: this.$route.params.id,
            idname: '',
            idcard: '',
            balance: '',
            password: '',
            start_at: '',
            name: ''
          }
        }
      },
      mounted () {
        this.getUserById()
      },
      methods: {
        cancel: function () {
          this.$router.push({name: 'userList'})
        },
        getUserById: function () {
          let array = {}
          array.method = 'getUserById'
          array.id = this.user.id
          UserApi.GetUserById(array).then(res => {
            this.user = JSON.parse(res.data.data)[0]
          })
        },
        checkout: function () {
          let array = {}
          array.method = 'checkout'
          array.computerId = this.user.computerId
          array.userId = this.user.id
          UserApi.Checkout(array).then(res => {
            if (res.data.status) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.$router.push({name: 'userList', params: { id: this.$route.params.id }})
            } else {
              this.$message.error(res.data.message)
            }
          })
        }
      }
    }
</script>

<style lang="scss" scoped>
  .userDisplay{
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
