<template>
  <div id="app">
    <el-container>
      <el-header>标题</el-header>
    </el-container>

    <el-container>
      <el-main>
        <el-tabs type="border-card">
          <el-tab-pane label="用户管理">用户管理</el-tab-pane>
          <el-tab-pane label="配置管理">配置管理</el-tab-pane>
          <el-tab-pane label="角色管理">角色管理</el-tab-pane>
          <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>

    <el-container>
      <el-footer>版权所有</el-footer>
    </el-container>
  </div>
</template>

<script>
import { index as fetchIndexData } from './api/index'

export default {
  name: 'App',
  data() {
    return {
      data: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      fetchIndexData().then(response => {
        this.data = response.data
        console.log(this.data)
      })
    }
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
