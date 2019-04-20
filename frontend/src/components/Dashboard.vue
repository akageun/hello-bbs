<template>
  <div>
    <gnb/>
    <bbs_list/>
  </div>
</template>

<script>
  import gnb from 'SrcRoot/components/layouts/gnb'
  import bbs_list from 'SrcRoot/components/bbs/bbs_list'

  export default {
    components: {
      gnb,
      bbs_list
    }
  }
</script>

<style scoped>

</style>

