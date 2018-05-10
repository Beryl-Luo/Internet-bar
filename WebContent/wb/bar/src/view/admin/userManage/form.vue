<template>
 <div class="userForm">
   <el-form ref="form" :inline="true"  :model="user" label-width="180px" style="text-align: left">
     <el-form-item label="姓名">
       <el-input  v-model="user.idname"></el-input>
     </el-form-item>
     <el-form-item label="身份证号">
       <el-input  v-model="user.idcard"></el-input>
     </el-form-item>
     <el-form-item label="选择会员等级">
       <el-select v-model="user.member_id" placeholder="选择会员等级" @change="changeMember(user.member_id)">
         <el-option v-for="member in members" :label="member.name" :value="member.id" :key="member.id"></el-option>
       </el-select>
     </el-form-item>
     <el-form-item label="优惠折数">
       <el-input v-model="favourable" disabled="disabled"></el-input>
     </el-form-item>
     <el-form-item label="余额(元)">
       <el-input  v-model="user.balance"></el-input>
     </el-form-item>
     <el-form-item label="密码">
       <el-input  v-model="user.password"></el-input>
     </el-form-item>
     <el-form-item style="display:block; text-align: center" >
       <el-button type="primary" @click="onSubmit">提交</el-button>
       <el-button @click="cancel()">取消</el-button>
     </el-form-item>
   </el-form>
 </div>
</template>

<script>
  import {UserApi} from './api'
    export default {
        name: 'userForm',
      data () {
        return {
          way: '',
          user: {
            id: this.$route.params.id,
            idname: '',
            idcard: '',
            balance: '',
            password: '',
            member_id: ''
          },
          members: [{
            id: '',
            name: '',
            favourable: ''
          }],
          favourable: ''
        }
      },
      mounted () {
        this.initMember()
        if (this.user.id === undefined) {
          this.way = 'add'
        } else {
          this.way = 'edit'
          this.getUserById()
        }
      },
      methods: {
        onSubmit: function () {
          if (this.way === 'add') {
              this.addUser()
          }
          if (this.way === 'edit') {
            this.editUser()
          }
        },
        cancel: function () {
          this.$router.push({name: 'userList'})
        },
        initMember: function () {
          let array = {}
          array.method = 'getAllMember'
          UserApi.GetAllUserMember(array).then(res => {
            this.members = JSON.parse(res.data.data)
          })
        },
        changeMember: function (id) {
          let obj = {}
          obj = this.members.find((value) => {
            return value.id === id
          })
          this.favourable = obj.favourable
        },
        editUser: function () {
          let array = {}
          array.method = 'editUser'
          array.id = this.user.id
          array.idname = this.user.idname
          array.idcard = this.user.idcard
          array.balance = this.user.balance
          array.password = this.user.password
          array.member_id = this.user.member_id
          UserApi.EditUser(array).then(res => {
            if (res.data.status) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.cancel()
            } else {
              this.$message.error(res.data.message)
            }
          })
        },
        addUser: function () {
          let array = {}
          array = this.user
          array.method = 'addUser'
          return UserApi.AddUser(array).then(res => {
            if (res.data.status) {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.cancel()
            } else {
              this.$message.error(res.data.message)
            }
          })
        },
        getUserById: function () {
          let array = {}
          array.method = 'getUserById'
          array.id = this.user.id
          UserApi.GetUserById(array).then(res => {
            this.user = JSON.parse(res.data.data)[0]
            this.user.id = this.$route.params.id
            this.favourable = JSON.parse(res.data.data)[0].favourable
          })
        }
      }
    }
</script>

<style scoped>

</style>
