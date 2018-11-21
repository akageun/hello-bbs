<template>
  <div>
    <gnb/>
    <bbs_list/>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'
  import bbs_list from '@/components/bbs/bbs_list'

  export default {
    data() {
      return {
      }
    },
    components: {
      gnb, bbs_list
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

