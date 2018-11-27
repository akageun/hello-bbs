<template>
  <div class="modal fade" id="signup_modal_id" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Sign up</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="signup_user_id">User Id</label>
            <input type="email" id="signup_user_id" v-model="userId" class="form-control" placeholder="User ID를 입력해주세요." required autofocus>
          </div>
          <div class="form-group">
            <label for="signup_password">Password</label>
            <input type="password" id="signup_password" v-model="passWd" class="form-control" placeholder="비밀번호를 입력해주세요." required>
          </div>
          <div class="form-group">
            <label for="signup_password_confirm">Password</label>
            <input type="password" id="signup_password_confirm" v-model="passWdConfirm" class="form-control" placeholder="비밀번호를 한번 더 입력해주세요."
                   required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="signup">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "signup",
    data() {
      return {
        userId: '',
        passWd: '',
        passWdConfirm: ''
      }
    },
    methods: {
      openModal(modalId) {
        $("#" + modalId).modal({
          'backdrop': 'static'
        });
      },
      signup() {
        const userId = this.userId;
        const passWd = this.passWd;
        const passWdConfirm = this.passWdConfirm;

        this.$store.dispatch('SIGNUP', {userId, passWd, passWdConfirm});
      }
    }

  }
</script>

<style scoped>
</style>
