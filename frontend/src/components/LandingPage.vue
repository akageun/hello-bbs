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
        msg: ''
      }
    },
    components: {
      gnb, bbs_list
    },
    created() {
      this.getCall();
    },
    methods: {
      getCall: function () {
        // axios.get('/api/test')
        //   .then(result => {
        //     this.msg = result.data;
        //   })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

