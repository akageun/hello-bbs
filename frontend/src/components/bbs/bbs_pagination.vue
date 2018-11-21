<template>
  <div class="row">
    <div class="col">
      <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
          <li class="page-item" v-if="preview()">
            <a class="page-link" @click="movePage(firstBlockPageNo)" tabindex="-1">&laquo;</a>
          </li>
          <li class="page-item" v-if="preview()">
            <a class="page-link" @click="movePage(preBlockPageNo)" tabindex="-1">&lt;</a>
          </li>
          <li v-for="(data, index) in $store.state.pageRange" class="page-item " :class="{active: isActive(data)}" v-if="data <=
          $store.state.bbs.pagination.totalPages">

            <a class="page-link" @click="movePage(data)">{{data}}</a>
          </li>

          <li class="page-item" v-if="nextPage()">
            <a class="page-link" @click="movePage(nextBlockNo)" tabindex="-1">&gt;</a>
          </li>
          <li class="page-item" v-if="nextPage()">
            <a class="page-link" @click="movePage(lastPageNo)" tabindex="-1">&raquo;</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
  export default {
    name: "bbs_pagination",
    data() {
      return {}
    },

    computed: {
      firstBlockPageNo() {
        return this.$store.state.bbs.pagination.firstPageNo;
      },
      preBlockPageNo() {
        return this.$store.state.bbs.pagination.preBlockPageNo;
      },
      nextBlockNo() {
        return this.$store.state.bbs.pagination.nextBlockPageNo + 1;
      },
      lastPageNo() {
        return this.$store.state.bbs.pagination.lastPageNo;
      },
    },
    methods: {
      movePage(pageIndex) {
        this.$store.state.pageIndex = (pageIndex - 1);

        this.$store.dispatch('GET_BBS_LIST', {});

        console.log(this.$store.state.bbs.pagination)
      },
      isActive(i) {
        return i === this.$store.state.bbs.pagination.pageNumber;
      },
      preview() {
        return this.$store.state.bbs.pagination.pageNumber > this.$store.state.bbs.pagination.pageBlockSize;
      },
      nextPage() {
        return this.$store.state.bbs.pagination.totalPages > this.$store.state.bbs.pagination.nextBlockPageNo;
      }
    }
  }
</script>

<style scoped>

</style>
