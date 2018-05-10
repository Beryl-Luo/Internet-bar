<template>
  <div class="netPlay">
    <el-button @click="selectComputer">选择机器</el-button>
    <el-form :model="user" :rules="rules" ref="ruleForm" label-position="left" label-width="0px"
             class="demo-ruleForm login-container">
      <h3 class="title">上网</h3>
      <el-form-item prop="login">
        <el-input type="text" v-model="user.login" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="user.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%;" type="primary" @click.native.prevent="submitForm('ruleForm')">登陆
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {LoginApi} from './api'
    export default {
        name: 'netPlay',
      data () {
        return {
          user: {
            computerId: this.$route.params.id,
            login: '',
            password: ''
          },
          rules: {
            login: [
              {required: true, message: '请输入身份证号', trigger: 'blur'},
              {min: 18, max: 18, message: '长度18个字符', trigger: 'blur'}
            ],
            password: [
              {required: true, message: '请输入登陆密码', trigger: 'change'}
            ]
          }
        }
      },
      methods: {
        getUser: function () {
        },
        submitForm: function (formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let array = {}
              array.method = 'clientLogin'
              array.computerId = this.user.computerId
              array.username = this.user.login
              array.password = this.user.password
              LoginApi.Login(array).then(res => {
                if (res.data.status) {
                  this.$message({
                    message: res.data.message,
                    type: 'success'
                  })
                  this.$router.push({name: 'netPlayMessage', params: { id: this.user.computerId }})
                } else {
                  this.$message.error(res.data.message)
                  return false
                }
              })
            }
          })
        },
        selectComputer: function () {
          this.$router.push({name: 'netPlay'})
        }
      }
    }
</script>
<style lang="scss" scoped>
  .netPlay {
    width: 100%;
    height: 100%;
  }

  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -60%);
    overflow: hidden;
    min-width: 270px;
    .title {
      text-align: center;
      color: #505458;
    }
  }
</style>
