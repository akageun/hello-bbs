<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col">
        <button class="btn btn-info btn-sm">실행</button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index",
    head() {
      console.log(this);

      return {
        title: '테스트',
        meta: [
          {hid: 'description', name: 'description', content: 'About our company Nuxt.js '}
        ]
      }
    }
  }
</script>

<style scoped>

</style>
