<template>
  <div>
    <gnb/>
    <bbsList/>
  </div>
</template>

<script>
  import gnb from 'SrcRoot/components/layouts/gnb';
  import bbsList from 'SrcRoot/components/bbs/list';

  export default {
    name: "dashboard",
    components: {
      gnb,
      bbsList
    }
  }
</script>

<style scoped>

</style>
