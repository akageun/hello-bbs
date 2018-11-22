<template>
  <div>
    <gnb/>
    <bbs_list/>
    <bbs_pagination/>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'
  import bbs_list from '@/components/bbs/bbs_list'
  import Bbs_pagination from "@/components/bbs/bbs_pagination";

  export default {
    components: {
      gnb, bbs_list, Bbs_pagination
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

