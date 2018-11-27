<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-12 offset-md-1 col-md-10 col-lg-10 offset-lg-1">
        <h4>
          Board
          <span class="float-right">
            <router-link to="/bbs/write" class="btn btn-info btn-sm ml-2">
              글쓰기
            </router-link>
          </span>
        </h4>
        <ul class="timeline">
          <li v-for="(data, index) in resultList">
            <span class="badge badge-primary">{{data.bbsCategoryEntity.name}}</span>
            <router-link :to="{path: '/bbs/'+ data.articleId}">{{data.title}}</router-link>

            <span href="#" class="float-right">{{data.createdAt}}</span>
            <p class="border-bottom mb-1 pt-2">
              {{data.content | liveSubstr}}
            </p>
            <p>
              <span class="float-right">
                {{data.createdUserId}}
              |
              <router-link :to="{path: '/bbs/write/'+ data.articleId}">Modify</router-link>
              </span>
            </p>
            <div class="clearfix"></div>
          </li>

        </ul>
      </div>
    </div>

    <bbs_pagination :propsPagination="pagination" :pageRange="pageRange"/>
  </div>
</template>

<script>
  import Bbs_pagination from "@/components/bbs/bbs_pagination";

  export default {
    name: "bbs_list",
    components: {
      Bbs_pagination
    },
    data() {
      return {
        pagination: {},
        resultList: {},
        pageRange: []
      }
    },
    created() {
      this.bbsList();

    },

    filters: {
      liveSubstr: function (str) {
        if (str.length < 100) {
          return str;
        }

        return str.substring(0, 100) + '...';
      }

    },
    methods: {
      bbsList() {
        this.$store.dispatch('GET_BBS_LIST', {})
          .then((data) => {
            this.resultList = data.resultList;
            this.pagination = data.pagination;

            this.pageRange = [];

            for (let i = this.pagination.firstBlockPageNo; i < this.pagination.lastBlockPageNo; i++) {
              this.pageRange.push(i);
            }
          })
          .catch(({message}) => {

          });
      }
    }
  }
</script>

<style scoped>
  ul.timeline {
    list-style-type: none;
    position: relative;
  }

  ul.timeline:before {
    content: ' ';
    background: #d4d9df;
    display: inline-block;
    position: absolute;
    left: 29px;
    width: 2px;
    height: 100%;
    z-index: 400;
  }

  ul.timeline > li {
    margin: 20px 0;
    padding-left: 20px;
  }

  ul.timeline > li:before {
    content: ' ';
    background: white;
    display: inline-block;
    position: absolute;
    border-radius: 50%;
    border: 3px solid #22c0e8;
    left: 20px;
    width: 20px;
    height: 20px;
    z-index: 400;
  }

  ul.timeline hr {
    margin: 0px;
  }
</style>
