<template>
  <div>
    <gnb/>

    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <router-link to="/bbs/write" class="btn btn-primary btn-sm">
            글쓰기
          </router-link>
        </div>
        <div class="col">
          <router-link to="/bbs/1" class="btn btn-primary btn-sm">
            글 보기
          </router-link>
        </div>
        <div class="col">
          <router-link to="/bbs/write/1" class="btn btn-primary btn-sm">
            글 수정
          </router-link>
        </div>
        <div class="col">
          {{msg}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'

  export default {
    data() {
      return {
        msg: ''
      }
    },
    components: {
      gnb
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
