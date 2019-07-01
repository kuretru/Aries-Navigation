<template>
  <div id="app">
    <el-container>
      <el-header>标题</el-header>
    </el-container>

    <el-container>
      <el-main>
        <el-tabs type="border-card">
          <el-tab-pane v-for="tag in tags" :key="tag.name" :label="tag.name">
            <el-tabs type="card" tab-position="left">
              <el-tab-pane v-for="category in tag.categories" :key="category.name" :label="category.name">
                <el-row>
                  <el-col v-for="site in category.sites" :key="site.name" :span="4">
                    <div>
                      <el-image :src="site.imageUrl" />
                      <el-link target="_blank" :href="site.siteUrl">
                        {{ site.name }}
                      </el-link>
                    </div>
                  </el-col>
                </el-row>
              </el-tab-pane>
            </el-tabs>
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
