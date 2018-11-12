<template>
  <div>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          1
        </div>
        <div class="col">
          2
        </div>
        <div class="col">
          3
        </div>
        <div class="col">
          4
        </div>
        <div class="col">
          {{msg}}
        </div>
      </div>
    </div>
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
