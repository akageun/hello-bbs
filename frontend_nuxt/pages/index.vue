<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <bbs_list/>
      <bbs_pagination/>
    </div>

  </div>
</template>

<script>
  import bbs_list from "@/components/bbs/bbs_list";
  import Bbs_pagination from "@/components/bbs/bbs_pagination";;
  import gnb from "@/components/layouts/gnb";;

  export default {
    name: "index",
    middleware: 'auth',
    components: {
      gnb,
      Bbs_pagination,
      bbs_list
    }

  }
</script>

<style scoped>

</style>
