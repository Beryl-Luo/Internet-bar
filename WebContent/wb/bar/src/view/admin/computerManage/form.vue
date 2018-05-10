<template>
   <div class="computerForm">
     <el-form ref="form" :inline="true"  :model="computer" label-width="180px">
       <el-form-item label="机器号">
         <el-input  v-model="computer.number"></el-input>
       </el-form-item>
       <el-form-item label="选择区域">
         <el-select v-model="computer.computerAreasId" placeholder="选择区域" @change="changeArea(computer.computerAreasId)">
           <el-option v-for="computerArea in computerAreas" :label="computerArea.name" :value="computerArea.id" :key="computerArea.charging"></el-option>
         </el-select>
       </el-form-item>
       <el-form-item label="收费（元/小时）">
         <el-input v-model="charging" disabled="disabled"></el-input>
       </el-form-item>
       <el-form-item style="display:block">
         <el-button type="primary" @click="onSubmit">提交</el-button>
         <el-button @click="cancel()">取消</el-button>
       </el-form-item>
     </el-form>
   </div>
</template>

<script>
  import {ComputerApi} from './api'
  export default {
    name: 'computerForm',
    data () {
      return {
        way: '',
        computer: {
          id: this.$route.params.id,
          number: '',
          computerAreasId: ''
        },
        computerAreas: [{
          id: '',
          name: '',
          charging: ''
        }],
        charging: ''
      }
    },
    mounted () {
      this.initComputerArea()
      if (this.computer.id === undefined) {
        this.way = 'add'
      } else {
        this.way = 'edit'
        this.getComputerById()
      }
    },
    methods: {
      onSubmit: function () {
        if (this.way === 'add') {
          this.addComputer()
        }
        if (this.way === 'edit') {
          this.editComputer()
        }
      },
      cancel: function () {
        this.$router.push({name: 'computerList'})
      },
      initComputerArea: function () {
        ComputerApi.GetAllComputerArea().then(res => {
          this.computerAreas = res.data
          console.log(res.data)
        })
      },
      changeArea: function (id) {
        let obj = {}
        obj = this.computerAreas.find((value) => {
          return value.id === id
        })
        this.charging = obj.charging
      },
      editComputer: function () {
        let array = {}
        array.method = 'editComputer'
        array.id = this.computer.id
        array.number = this.computer.number
        array.type_id = this.computer.computerAreasId
        ComputerApi.EditComputer(array).then(res => {
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
      addComputer: function () {
        let array = {}
        array = this.computer
        array.method = 'addComputer'
        return ComputerApi.AddComputer(array).then(res => {
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
      getComputerById: function () {
        let array = {}
        array.method = 'getComputerById'
        array.id = this.computer.id
        ComputerApi.GetComputerById(array).then(res => {
          this.computer.number = JSON.parse(res.data.data)[0].number
          this.computer.computerAreasId = JSON.parse(res.data.data)[0].type_id
          this.charging = JSON.parse(res.data.data)[0].charging
        })
      }
    }
  }
</script>

<style scoped>

</style>
