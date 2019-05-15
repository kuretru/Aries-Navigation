<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">{{ scope.row.id }}</template>
      </el-table-column>
      <el-table-column label="标签名称" width="100" align="center">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { list } from '@/api/tag'

export default {
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {
        console.log(response)
        this.list = response.data
        this.listLoading = false
      })
    }
  }
}
</script>
