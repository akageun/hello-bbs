<template>
  <div>
    <login-form/>
    <signup-form/>
  </div>
</template>

<script>

  import SignupForm from "../components/user/signupForm";
  import LoginForm from "../components/user/loginForm";

  export default {
    name: "login",
    components: {LoginForm, SignupForm},
  }
</script>

<style scoped>

</style>
