<template>
  <el-col
    :span="3"
    :offset="index > 0 && index % 6 == 0 ? 6 : 0"
  >
    <div>
      <el-image
        :src="site.imageUrl"
        :fit="fill"
        style="width: 24px; height: 24px;"
      />
      <el-link
        :href="site.siteUrl"
        target="_blank"
      >
        {{ site.name }} && {{ index }}
      </el-link>
    </div>
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
          imageUrl: "https://blog.kuretru.com/images/favicon-32x32-next.png"
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
