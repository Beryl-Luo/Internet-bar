<template>
  <div class="bar_nav">
  <el-aside width="200px">
    <el-menu :default-openeds="['1']" :default-active="openMenuIndex"
             class="el-menu-vertical-demo"
             background-color="rgb(48, 65, 86)"
             text-color="rgb(191, 203, 217)"
             active-text-color="rgb(64, 158, 255)">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-menu"></i>网吧管理</template>
        <el-menu-item-group>
          <router-link class="head-link" to="/admin">
            <el-menu-item index="1-1"><i class="el-icon-star-on"></i>首页</el-menu-item>
          </router-link>
          <router-link class="head-link" to="/admin/user">
            <el-menu-item index="1-2"><i class="el-icon-edit-outline"></i>用户管理 </el-menu-item>
          </router-link>
          <router-link class="head-link" to="/admin/computer">
            <el-menu-item index="1-3"><i class="el-icon-mobile-phone"></i>机器管理</el-menu-item>
          </router-link>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
  </div>
</template>

<script>
    export default {
      name: 'nav',
      data () {
        return {
          openMenuIndex: '1-1'
        }
      },
      components: {},
      methods: {},
      mounted () {
      }
    }
</script>
<style scoped lang="scss">
  .bar_nav{
    min-height: 300px;
    background-color: rgb(48, 65, 86);
  }
  .head-link{
    text-decoration: none;
  }
  .el-menu-item{
    text-align: left;
  }
  .el-menu-item:hover{
    background-color: cadetblue !important;
  }

</style>
