<template>
  <div>
    <div class="row">
      <div class="col">
        <div class="float-right">
          <div class="input-group mb-2">
            <nuxt-link to="/bbs/write" class="btn btn-primary btn-sm ml-2">
              글쓰기
            </nuxt-link>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="table-responsive">
        <table class="table">
          <thead class="thead-light">
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Created at</th>
            <th>Created at</th>
            <th>Function</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(data, index) in $store.state.bbs.bbsList" :key="index">

            <td>
              <nuxt-link :to="{path: '/bbs/'+ data.articleId}">{{data.articleId}}</nuxt-link>
            </td>
            <td>
              <nuxt-link :to="{path: '/bbs/'+ data.articleId}">{{data.title}}</nuxt-link>
            </td>
            <td>{{data.createdUserId}}</td>
            <td>{{data.createdAt}}</td>
            <td>{{data.updatedAt}}</td>
            <td>
              <nuxt-link :to="{path: '/bbs/write/'+ data.articleId}" class="btn btn-warning btn-sm">
                수정
              </nuxt-link>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
    </div>

  </div>
</template>

<script>

  export default {
    name: "bbs_list",
    computed: {},
    created() {

      this.getArticle()
    },
    methods: {
      getArticle() {

        this.$store.dispatch('GET_BBS_LIST', {});
      }
    }
  }
</script>

<style scoped>

</style>
