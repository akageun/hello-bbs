<template>
  <div>
    <div class="row">
      <div class="col">
        <nav aria-label="Page navigation">
          <ul class="pagination justify-content-center">

            <li class="page-item" v-show="preview()">
              <a class="page-link" href="#" tabindex="-1">&laquo;</a>
            </li>
            <li class="page-item" v-show="preview()">
              <a class="page-link" href="#" tabindex="-1">&lt;</a>
            </li>




            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item" v-show="nextPage()">
              <a class="page-link" href="#" tabindex="-1">&laquo;</a>
            </li>
            <li class="page-item" v-show="nextPage()">
              <a class="page-link" href="#" tabindex="-1">&lt;</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "bbs_pagination",
    methods: {
      preview() {
        return this.$store.state.bbs.pagination.pageNumber > this.$store.state.bbs.pagination.pageBlockSize;
      },
      nextPage(){
        return this.$store.state.bbs.pagination.totalPages > this.$store.state.bbs.pagination.nextBlockPageNo;
      }
    }
  }
</script>

<style scoped>

</style>
