<template>
    <div class="userManage">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>用户管理</span>
        </div>
        <router-view/>
      </el-card>
    </div>
</template>

<script>
    export default {
        name: 'userManage'
    }
</script>

<style scoped>

</style>
