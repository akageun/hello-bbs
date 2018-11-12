<template>
  <div>
    <button class="btn">테스트</button>
    {{msg}}
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        msg: ''
      }
    },
    created() {
      this.getCall();
    },
    methods: {
      getCall: function () {
        axios.get('/api/test')
          .then(result => {
            this.msg = result.data;
          })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
