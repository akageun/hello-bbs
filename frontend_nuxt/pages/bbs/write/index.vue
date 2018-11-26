<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <bbs_write/>
    </div>
  </div>
</template>

<script>
  import bbs_write from "@/components/bbs/bbs_write";
  import gnb from "@/components/layouts/gnb";

  export default {
    name: "index",
    middleware: 'auth',
    components: {
      gnb,
      bbs_write
    }
  }
</script>

<style scoped>

</style>
