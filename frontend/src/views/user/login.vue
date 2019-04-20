<template>
  <div>
    <login/>
    <signup/>
  </div>
</template>

<script>
  import signup from 'SrcRoot/components/user/signup';
  import login from 'SrcRoot/components/user/login';

  export default {
    components: {
      signup, login
    }
  }
</script>

<style scoped>

</style>
