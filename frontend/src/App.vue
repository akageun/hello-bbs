<template>
  <div id="app">
    <gnb/>
    <br>
    <router-view/>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'

  export default {
    name: 'App',
    components: {
      gnb
    }
  }
</script>

<style>

</style>
