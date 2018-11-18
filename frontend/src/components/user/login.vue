<template>
  <div>
    <div class="form-signin bg-light mt-5 shadow-lg bg-white rounded">
      <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Hello, BBS</h1>
        <p>
          Spring Boot &amp; Vue.js 를 사용한 토이프로젝트 입니다.
        </p>
      </div>

      <div class="form-group">
        <label for="user_id">User Id</label>
        <input type="email" id="user_id" name="userId" v-model="userId" class="form-control" placeholder="User ID를 입력해주세요." required autofocus>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="passwd" v-model="passWd" class="form-control" placeholder="비밀번호를 입력해주세요." required>
      </div>

      <button class="btn btn-lg btn-primary btn-block" @click="login">Sign in</button>

      <br>
      <div class="form-group text-center">
        <a href="#" class="mr-3" @click="openModal('signup_modal_id')">Sign Up</a>
        <a href="https://github.com/akageun/hello-bbs">GITHUB</a>
      </div>
    </div>

    <signup/>
  </div>
</template>

<script>
  import signup from '@/components/user/signup';

  export default {
    name: "login",
    components: {
      signup
    },
    data() {
      return {
        userId: '',
        passWd: ''
      }
    },
    methods: {
      openModal(modalId) {
        $("#" + modalId).modal({
          'backdrop': 'static'
        });
      },
      login() {
        const userId = this.userId;
        const passWd = this.passWd;

        this.$store.dispatch('LOGIN', {userId, passWd})
          .then(() => {

          })
          .catch(({message}) => this.msg = message)
      }
    }
  }
</script>

<style scoped>
  html,
  body {
    height: 100%;
  }

  body {
    padding-top: 40px;
    padding-bottom: 40px;
  }

  .form-signin {
    width: 100%;
    max-width: 420px;
    padding: 15px;
    margin: auto;
  }
</style>
