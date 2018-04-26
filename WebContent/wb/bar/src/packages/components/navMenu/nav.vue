<template>
  <div class="bar_nav">
  <el-aside width="200px">
    <el-menu :default-openeds="['1']" :default-active="openMenuIndex"
             class="el-menu-vertical-demo"
             background-color="rgb(48, 65, 86)"
             text-color="rgb(191, 203, 217)"
             active-text-color="rgb(64, 158, 255)">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>导航一</template>
        <el-menu-item-group>
          <router-link class="head-link" to="/">
            <el-menu-item index="1-1">首页</el-menu-item>
          </router-link>
          <router-link class="head-link" to="/payment">
            <el-menu-item index="1-2">其他 </el-menu-item>
          </router-link>
          <router-link class="head-link" to="/about">
            <el-menu-item index="1-3">关于</el-menu-item>
          </router-link>
          <router-link class="head-link" to="/editor">
            <el-menu-item index="1-4">editor </el-menu-item>
          </router-link>
          <router-link class="head-link" to="/new-vuex">
            <el-menu-item index="1-5"> new-vuex </el-menu-item>
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

</style>
