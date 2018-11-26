<template>
  <div>
    <gnb/>

    <div class="container-fluid">
      <bbs_detail/>
    </div>

  </div>
</template>

<script>
  import bbs_detail from "@/components/bbs/bbs_detail";
  import gnb from "@/components/layouts/gnb";

  export default {
    name: "index",
    middleware: 'auth',
    components: {
      gnb,
      bbs_detail
    }

  }
</script>

<style scoped>

</style>
