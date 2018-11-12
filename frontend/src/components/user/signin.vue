<template>
  <div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog " role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">


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
    name: "signin"
  }
</script>

<style scoped>
  /*.modal {*/
    /*text-align: center;*/
  /*}*/

  /*@media screen and (min-width: 768px) {*/
    /*.modal:before {*/
      /*display: inline-block;*/
      /*vertical-align: middle;*/
      /*content: " ";*/
      /*height: 100%;*/
    /*}*/
  /*}*/

  /*.modal-dialog {*/
    /*display: inline-block;*/
    /*text-align: left;*/
    /*vertical-align: middle;*/
  /*}*/
</style>
