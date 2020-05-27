<template>
  <el-col
    :span="3"
    :offset="index > 0 && index % 6 == 0 ? 0 : 0"
  >
    <el-popover
      trigger="hover"
      :content="site.description"
      :disabled="site.description === ''"
      width="300"
    >
      <div
        slot="reference" 
        style="float: left;"
      >
        <el-image
          :src="site.imageUrl"
          fit="fill"
        >
          <div slot="error">
            <i class="el-icon-guide" />
          </div>
        </el-image>
        <el-link
          :href="site.siteUrl"
          target="_blank"
        >
          {{ site.name }}
        </el-link>
      </div>
    </el-popover>
  </el-col>
</template>

<script>
export default {
  name: "WebSite",
  props: {
    site: {
      type: Object,
      default() {
        return {
          name: "呉真的博客",
          siteUrl: "https://blog.kuretru.com/",
          imageUrl: "https://blog.kuretru.com/images/favicon-32x32-next.png",
          description: "呉真的博客，生命在于折腾"
        };
      }
    },
    index: {
      type: Number,
      default: 0
    }
  }
};
</script>

<style>
.el-image{
  width:1em;
  height:1em;
}
</style>
