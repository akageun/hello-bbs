<template>
  <div>
    <!-- Modal -->
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
              <label for="user_id">User Id</label>
              <input type="email" id="user_id" name="userId" class="form-control" placeholder="User ID를 입력해주세요." required autofocus>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" id="password" name="passwd" class="form-control" placeholder="비밀번호를 입력해주세요." required>
            </div>
            <div class="form-group">
              <label for="password_confirm">Password</label>
              <input type="password" id="password_confirm" name="passwdConfirm" class="form-control" placeholder="비밀번호를 한번 더 입력해주세요." required>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "signup"
  }
</script>

<style scoped>
</style>
