<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select
        v-model="tagId"
        placeholder="标签"
        class="filter-item"
        style="width: 130px"
        @change="onTagChange"
      >
        <el-option v-for="item in tagList" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
      <el-select
        v-model="categoryId"
        placeholder="分类"
        class="filter-item"
        style="width: 130px"
        @change="onCategoryChange"
      >
        <el-option v-for="item in categoryList" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >新增</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-sort"
        @click="handlerReorder"
      >保存新顺序</el-button>
    </div>

    <el-table
      ref="dragTable"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      row-key="id"
      stripe
      border
      fit
      highlight-current-row
    >
      <el-table-column prop="id" label="ID" align="center" width="95" />

      <el-table-column prop="imageUrl" label="图标" align="center" width="100">
        <template slot-scope="{row}">
          <img class="favicon" :src="row.imageUrl">
        </template>
      </el-table-column>

      <el-table-column prop="name" label="导航名称" align="center" width="100">
        <template slot-scope="{row}">
          <a :href="row.siteUrl" target="blank">{{ row.name }}</a>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>

      <el-table-column align="center" label="拖动排序" width="100">
        <svg-icon class="drag-handler" icon-class="drag" />
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="标签" prop="tagId">
          <el-select
            v-model="temp.tagId"
            class="filter-item"
            placeholder="Please select"
            @change="onTempTagChange"
          >
            <el-option
              v-for="item in temp.tagList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="temp.categoryId" class="filter-item" placeholder="Please select">
            <el-option
              v-for="item in temp.categoryList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="链接" prop="siteUrl">
          <el-input v-model="temp.siteUrl" @blur="onSiteUrlChange" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="temp.description" @blur="onSiteUrlChange" />
        </el-form-item>
        <el-form-item label="图标">
          <img :src="temp.imageUrl" class="favicon favicon-dialog">
          <el-button
            type="primary"
            icon="el-icon-refresh-right"
            circle
            size="mini"
            @click="resetFavicon"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list as listTags } from '@/api/tag'
import { list as listCategories } from '@/api/category'
import {
  list,
  create,
  update,
  remove,
  reorder,
  fetchFavicon
} from '@/api/site'
import Sortable from 'sortablejs'

export default {
  data() {
    return {
      tagList: null,
      tagId: null,
      categoryList: null,
      categoryId: null,
      list: [],
      idList: [],
      listLoading: true,
      sortable: null,
      temp: {
        tagId: 0,
        categoryId: 0,
        name: '',
        imageUrl: '',
        siteUrl: '',
        description: '',
        tagList: null,
        categoryList: null
      },
      dialogStatus: '',
      dialogFormVisible: false,
      textMap: {
        update: '编辑',
        create: '新增'
      },
      rules: {
        tagId: [{ required: true, message: '标签ID是必填项', trigger: 'blur' }],
        categoryId: [
          { required: true, message: '分类ID是必填项', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '站点名称是必填项', trigger: 'blur' }
        ],
        siteUrl: [
          { required: true, message: '站点地址是必填项', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.fetchTags()
  },
  methods: {
    onTagChange() {
      this.fetchCategories()
    },
    onCategoryChange() {
      this.fetchData()
    },
    onSiteUrlChange() {
      if (this.temp.siteUrl && !this.temp.imageUrl) {
        fetchFavicon(this.temp.tagId, this.temp.categoryId, this.temp.siteUrl)
          .then(response => {
            this.temp.imageUrl = response.data.url
            console.log(response)
          })
          .catch(error => {
            const response = error.response.data
            this.temp.imageUrl = ''
            console.log(response)
          })
      }
    },
    onTempTagChange() {
      listCategories(this.temp.tagId).then(response => {
        this.temp.categoryList = response.data
        this.temp.categoryId = this.temp.categoryList[0].id
        this.fetchData()
      })
    },
    fetchTags() {
      listTags().then(response => {
        this.tagList = response.data
        this.tagId = this.tagList[0].id
        this.fetchCategories()
      })
    },
    fetchCategories() {
      listCategories(this.tagId).then(response => {
        this.categoryList = response.data
        this.categoryId = this.categoryList[0].id
        this.fetchData()
      })
    },
    fetchData() {
      this.listLoading = true
      list(this.tagId, this.categoryId)
        .then(response => {
          this.list = response.data
          this.listLoading = false
          this.idList = this.list.map(v => v.id)
          this.$nextTick(() => {
            this.setSort()
          })
        })
        .catch(response => {
          this.list = []
          this.listLoading = false
        })
    },
    resetTemp() {
      this.temp = {
        tagId: this.tagId,
        categoryId: this.categoryId,
        name: '',
        imageUrl: '',
        siteUrl: '',
        tagList: this.tagList,
        categoryList: this.categoryList
      }
    },
    resetFavicon() {
      this.temp.imageUrl = ''
      this.onSiteUrlChange()
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.temp.tagId = this.tagId
      this.temp.tagList = this.tagList
      this.temp.categoryList = this.categoryList
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleDelete(row) {
      this.$confirm('是否删除导航【' + row.name + '】？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          remove(this.tagId, this.categoryId, row.id).then(() => {
            this.fetchData()
            this.$message({
              type: 'success',
              message: '分类信息删除成功!'
            })
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    handlerReorder() {
      this.$confirm('是否保存新顺序？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          reorder(this.tagId, this.categoryId, this.idList).then(response => {
            this.list = response.data
            this.$message({
              type: 'success',
              message: '新顺序保存成功!'
            })
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          create(this.temp).then(response => {
            if (
              this.tagId === this.temp.tagId &&
              this.categoryId === this.temp.categoryId
            ) {
              this.list.push(response.data)
            }
            this.dialogFormVisible = false
            this.$message({
              message: '导航信息新增成功',
              type: 'success'
            })
          })
        }
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          update(this.temp).then(response => {
            this.fetchData()
            this.dialogFormVisible = false
            this.$message({
              message: '导航信息修改成功',
              type: 'success'
            })
          })
        }
      })
    },
    setSort() {
      const el = this.$refs.dragTable.$el.querySelectorAll(
        '.el-table__body-wrapper > table > tbody'
      )[0]
      this.sortable = Sortable.create(el, {
        ghostClass: 'sortable-ghost',
        setData: function(dataTransfer) {
          dataTransfer.setData('Text', '')
        },
        onEnd: evt => {
          const tempIndex = this.idList.splice(evt.oldIndex, 1)[0]
          this.idList.splice(evt.newIndex, 0, tempIndex)
        }
      })
    }
  }
}
</script>

<style>
.sortable-ghost {
  opacity: 0.8;
  color: #fff !important;
  background: #42b983 !important;
}

.favicon {
  width: 24px;
  height: 24px;
  line-height: 24px;
  vertical-align: middle;
}

.favicon-dialog {
  margin: 0 20px 0 20px;
}
</style>

<style scoped>
.drag-handler {
  width: 20px;
  height: 20px;
  cursor: pointer;
}
</style>
