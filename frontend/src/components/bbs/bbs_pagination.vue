<template>
  <div class="row">
    <div class="col">
      <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
          <li class="page-item" v-if="propsPagination.pageNumber > propsPagination.pageBlockSize">
            <a class="page-link" @click="movePage(propsPagination.firstPageNo)" tabindex="-1">&laquo;</a>
          </li>
          <li class="page-item" v-if="propsPagination.pageNumber > propsPagination.pageBlockSize">
            <a class="page-link" @click="movePage(propsPagination.preBlockPageNo)" tabindex="-1">&lt;</a>
          </li>
          <li v-for="(data, index) in pageRange"
              class="page-item " :class="{active: isActive(data)}"
              v-if="data <= propsPagination.totalPages">

            <a class="page-link" @click="movePage(data)">{{data}}</a>
          </li>

          <li class="page-item" v-if="propsPagination.totalPages > propsPagination.nextBlockPageNo">
            <a class="page-link" @click="movePage(propsPagination.nextBlockPageNo + 1)" tabindex="-1">&gt;</a>
          </li>
          <li class="page-item" v-if="propsPagination.totalPages > propsPagination.nextBlockPageNo">
            <a class="page-link" @click="movePage(propsPagination.lastPageNo)" tabindex="-1">&raquo;</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
  export default {
    name: "bbs_pagination",
    props: {
      propsPagination: {
        type: Object,
        default() {
          return {};
        },
      },
      pageRange: {
        type: Array,
        default() {
          return {};
        },
      }
    },
    methods: {
      movePage(pageIndex) {
        this.$store.state.pageIndex = (pageIndex - 1);
        this.$parent.bbsList();
      },
      isActive(i) {
        return i === this.propsPagination.pageNumber;
      },
    }
  }
</script>

<style scoped>

</style>
