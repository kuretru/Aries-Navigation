<template>
  <div id="app">
    <el-container>
      <el-header>标题</el-header>
    </el-container>

    <el-container>
      <el-main>
        <el-tabs type="border-card">
          <el-tab-pane v-for="tag in tags" :key="tag.name" :label="tag.name">
            <el-row v-for="category in tag.categories" :key="category.name" :label="category.name">
              <el-col :span="6">
                <span>{{ category.name }}</span>
              </el-col>
              <el-col :span="18">
                <div v-for="site in category.sites" :key="site.name" style="display:inline-block;width:16.6%">
                  <div>
                    <el-image :src="site.imageUrl" style="width: 24px; height: 24px;" />
                    <el-link target="_blank" :href="site.siteUrl">
                      {{ site.name }}
                    </el-link>
                  </div>
                </div>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>

    <el-container>
      <el-footer>版权所有</el-footer>
    </el-container>
  </div>
</template>

<script>
import { list } from './api/data'

export default {
  name: 'App',
  data() {
    return {
      tags: [],
      categories: [],
      sites: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      list().then(response => {
        this.tags = response.data
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
