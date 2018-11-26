<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <bbs_modify/>
    </div>
  </div>
</template>

<script>
  import bbs_modify from "@/components/bbs/bbs_modify";
  import gnb from "@/components/layouts/gnb";

  export default {
    name: "index",
    middleware: 'auth',
    components: {
      gnb,
      bbs_modify
    }
  }
</script>

<style scoped>

</style>
