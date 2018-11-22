<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <div class="float-right">
          <div class="input-group mb-2">
            <router-link to="/bbs/write" class="btn btn-primary btn-sm ml-2">
              글쓰기
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="table-responsive">
        <table class="table">
          <colgroup>
            <col width="5%"/>
            <col width="*"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="5%"/>
          </colgroup>
          <thead class="thead-light">
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Created at</th>
            <th>Updated at</th>
            <th>Function</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(data, index) in $store.state.bbs.bbsList">
            <td>
              <router-link :to="{path: '/bbs/'+ data.articleId}">{{data.articleId}}</router-link>
            </td>
            <td>
              <router-link :to="{path: '/bbs/'+ data.articleId}">{{data.title}}</router-link>
            </td>
            <td>{{data.createdUserId}}</td>
            <td>{{data.createdAt}}</td>
            <td>{{data.updatedAt}}</td>
            <td>
              <router-link :to="{path: '/bbs/write/'+ data.articleId}" class="btn btn-warning btn-sm">
                수정
              </router-link>
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
    created() {
      this.$store.dispatch('GET_BBS_LIST', {});
    }
  }
</script>

<style scoped>

</style>
