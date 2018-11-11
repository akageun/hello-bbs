<template>
  <div>
    Test URI
  </div>
</template>

<script>
  export default {
    validate({params}) {
      console.log(params);
      // Must be a number
      return /^\d+$/.test(params.id)
    },
    name: "test",
    head() {

      return {
        title: '테스트 - HelloBBS',
        meta: [
          {hid: 'description', name: 'description', content: 'About our company Nuxt.js '}
        ]
      }
    }
  }
</script>

<style scoped>

</style>
