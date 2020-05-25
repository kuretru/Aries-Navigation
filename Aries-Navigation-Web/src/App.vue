<template>
  <div id="app">
    <el-container>
      <el-header>标题</el-header>
      <el-main>
        <el-tabs type="border-card">
          <el-tab-pane
            v-for="tag in tags"
            :key="tag.name"
            :label="tag.name"
          >
            <el-row
              v-for="category in tag.categories"
              :key="category.name"
              :label="category.name"
            >
              <el-col :span="6">
                <span>{{ category.name }}</span>
              </el-col>
              <WebSite
                v-for="(site,index) in category.sites"
                :key="site.name"
                :site="site"
                :index="index"
              />
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-main>
      <el-footer>版权所有</el-footer>
    </el-container>
  </div>
</template>

<script>
import { list } from "./api/data";
import WebSite from "./components/WebSite";

export default {
  name: "App",
  components: {
    WebSite
  },
  data() {
    return {
      tags: []
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      list().then(response => {
        this.tags = response.data;
      });
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.el-row {
  margin-top: 1em;
  margin-bottom: 1em;
}
</style>
