<template>
  <div>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <div class="float-right">
            <div class="input-group mb-2">
              <!--<div class="input-group-prepend">-->
              <!--<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon1">검색</button>-->
              <!--</div>-->
              <!--<input type="text" class="form-control form-control-sm" placeholder="" aria-label="Example text with button addon"-->
              <!--aria-describedby="button-addon1">-->
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
      <bbs_pagination/>
    </div>
  </div>
</template>

<script>
  import Bbs_pagination from "./bbs_pagination";

  export default {
    name: "bbs_list",
    components: {Bbs_pagination},
    created() {
      this.getArticle()
    },
    methods: {
      getArticle() {
        this.$store.dispatch('GET_BBS_LIST', {});
        // .then((data) => {
        //   //console.log(data);
        // })
        // .catch(({message}) => {
        //   console.log("err : ", message);
        // })
      }
    }
  }
</script>

<style scoped>

</style>
