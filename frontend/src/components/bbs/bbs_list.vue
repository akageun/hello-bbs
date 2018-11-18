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
              <th>Created at</th>
              <th>Function</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>
                <router-link to="/bbs/1">1</router-link>
              </td>
              <td>메롱</td>
              <td>akageun</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>
                <router-link to="/bbs/write/1" class="btn btn-primary btn-sm">
                  글 수정
                </router-link>
              </td>
            </tr>
            <tr>
              <td>
                <router-link to="/bbs/2">2</router-link>
              </td>
              <td>안녕하세요. 테스트 글 입니다.</td>
              <td>akageun</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>
                <router-link to="/bbs/write/1" class="btn btn-primary btn-sm">
                  글 수정
                </router-link>
              </td>
            </tr>
            <tr>
              <td>
                <router-link to="/bbs/3">3</router-link>
              </td>
              <td>테스트 세번째 글</td>
              <td>akageun</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>2018-11-16 12:00:00</td>
              <td>
                <router-link to="/bbs/write/1" class="btn btn-primary btn-sm">
                  글 수정
                </router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
              </li>
              <li class="page-item active"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#">Next</a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "bbs_list",
    created() {
      this.getArticle()
    },
    methods: {
      getArticle() {
        this.$store.dispatch('GET_BBS_LIST', {})
          .then((data) => {
            console.log(data);
          })
          .catch(({message}) => this.msg = message)
      }
    }
  }
</script>

<style scoped>

</style>
