<template>
    <div class="netPlayMessage">
      <h2>{{message.idname}}号机器</h2>
      <div style="min-height: 500px">
        <el-row :gutter="100" >
          <el-col :span="10">
            <label class="el-col-8 label-own">姓名</label>
            <span>{{message.idname}}</span>
          </el-col>
          <el-col :span="10" >
            <label class="el-col-8 label-own">身份证号</label>
            <span>{{message.idcard}}</span>
          </el-col>
        </el-row>
        <el-row :gutter="100">
          <el-col :span="10" >
            <label class="el-col-8 label-own">会员等级</label>
            <span>{{message.memberName}}</span>
          </el-col>
          <el-col :span="10" >
            <label class="el-col-8 label-own">余额(元)</label>
            <span>{{message.balance}}</span>
          </el-col>
        </el-row>
        <el-row :gutter="100" >
          <el-col :span="10">
            <label class="el-col-8 label-own">收费(元/小时)</label>
            <span>{{message.charging}}</span>
          </el-col>
          <el-col :span="10">
            <label class="el-col-8 label-own">上网开始时间</label>
            <span >{{new Date(message.start_at).toUTCString()}}</span>
          </el-col>
        </el-row>
        <el-button type="success" @click="checkout">结账下机</el-button>
      </div>
    </div>
</template>

<script>
  import {LoginApi} from './api'
    export default {
      name: 'netPlayMessage',
      data () {
          return {
            message: {
              computerId: this.$route.params.id,
              number: '',
              userId: '',
              idname: '',
              idcard: '',
              start_at: '1',
              charging: '',
              memberName: '',
              balance: '',
              favourable: ''
            },
            statusCel: 0
          }
      },
      mounted () {
        this.initMessage()
        this.startTime()
      },
      methods: {
        initMessage: function () {
          let array = {}
          array.method = 'selectMessageByComputerId'
          array.computerId = this.message.computerId
          LoginApi.SelectMessageByComputerId(array).then(res => {
            this.message = JSON.parse(res.data.data)
            if (JSON.parse(res.data.data).balance < JSON.parse(res.data.data).charging) {
              this.$message.error('请充值！')
              this.checkout()
            } else {
              this.changeBalance()
            }
          })
        },
        startTime: function () {
          let timer2 = setInterval(() => {
            if (this.statusCel === 1) {
              clearInterval(timer2)
            } else {
              this.changeBalance()
            }
          }, 3600000)
        },
        changeBalance: function () {
          let array = {}
          array.method = 'updataBalance'
          array.userId = this.message.userId
          if (this.message.balance - this.message.charging > 0) {
            array.balance = this.message.balance - this.message.charging
            LoginApi.UpdataBalance(array).then(res => {
              if (res.data.status) {
                this.$message({
                  message: res.data.message,
                  type: 'success'
                })
                this.message.balance = array.balance
              } else {
                this.$message.error(res.data.message)
              }
            })
          } else {
            this.statusCel = 1
            let surplusTime = (this.message.balance / this.message.charging) * 3600000
            array.balance = 0
            let timer2 = setInterval(() => {
              LoginApi.UpdataBalance(array).then(res => {
                clearInterval(timer2)
                if (res.data.status) {
                  this.$message({
                    message: '请充值！欢迎下次光临',
                    type: 'success'
                  })
                  this.checkout()
                } else {
                  this.$message.error(res.data.message)
                }
              })
            }, surplusTime)
          }
        },
        checkout: function () {
          let array = {}
          array.method = 'checkout'
          array.computerId = this.message.computerId
          array.userId = this.message.userId
          LoginApi.Checkout(array).then(res => {
            if (res.data.status) {
              this.$router.push({name: 'netPlayLogin', params: { id: this.$route.params.id }})
            } else {
              this.$message.error(res.data.message)
            }
          })
        }

      }
    }
</script>

<style lang="scss" scoped>
  .netPlayMessage{
    position: absolute;
    top: 50%;
    left: 50%;
    min-width: 1000px;
    transform: translate(-50%, -60%);
  .label-own {
    font-size: 15px;
    font-weight:600;
    text-align: right;
  }
  span{
    font-size: 14px;
    padding-left: 10px;
  }
  .el-col{
    height: 50px;
  }
  }

</style>
