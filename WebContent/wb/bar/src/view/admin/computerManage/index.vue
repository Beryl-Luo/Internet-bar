<template>
    <div class="computer">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>机器管理</span>
        </div>
        <router-view/>
      </el-card>
    </div>
</template>

<script>
    export default {
        name: 'computer'
    }
</script>

<style scoped>

</style>
