<template>
  <div class="container-fluid">
    <div class="row mb-3">
      <div class="col-lg-9 col-md-12">
        <input type="text" id="title" class="form-control" placeholder="제목을 입력해주세요."/>
      </div>
      <div class="col-lg-3 col-md-12">
        <select id="category_id" name="categoryId" class="form-control select2-multiple tmpSelect2">
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <textarea id="bbs_write_area"></textarea>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <button type="button" class="btn btn-block btn-outline-success" @click="saveData">
          SAVE
        </button>
      </div>
    </div>
  </div>
</template>

<script>
  import router from 'SrcRoot/router'
  import $ from 'jquery'

  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_modify",
    data() {
      return {
        simpleMde: null,
        articleId: null
      }
    },
    mounted() {
      this.simpleMde = new SimpleMDE({
        element: document.getElementById("bbs_write_area"),
        spellChecker: false,
      });

      this.articleId = this.$route.params.articleId;
      const articleId = this.articleId;

      this.$store.dispatch('GET_BBS', {articleId})
        .then((data) => {
          document.getElementById('title').value = data.data.data.title;

          this.simpleMde.value(data.data.data.content);
          let html = "<option value='" + data.data.data.categoryId + "' selected='selected'>";
          html += data.data.data.bbsCategoryEntity.name;
          html += "</option>";


          $("#category_id").append(html);

          $(' .tmpSelect2').select2({
            placeholder: "카테고리를 선택해주세요.",
            theme: "bootstrap",
            minimumInputLength: 2,
            delay: 250,
            ajax: {
              type: 'GET',
              url: '/api/category/v1/search',
              dataType: "json",
              headers: {
                "Authorization": "Bearer " + localStorage.getItem('tk'),
                "Content-Type": "application/json",
              },
              data: function (params) {
                return {
                  keyword: params.term
                };
              },
              processResults: function (data) {
                return {
                  results: $.map(data.data, function (item) {
                    return {
                      text: item.name,
                      id: item.categoryId
                    }
                  })
                };
              }
            }
          });
        })
        .catch(({message}) => {
          console.log("err : ", message);
        });
    },
    methods: {
      saveData() {
        const title = document.getElementById('title').value;
        const content = this.simpleMde.value();
        const categoryId = document.getElementById('category_id').value;
        const statusCd = "NORMAL";

        const articleId = this.articleId;

        this.$store.dispatch('MODIFY_BBS_ARTICLE', {articleId, title, content, statusCd, categoryId})
          .then((data) => {
            console.log(data);
            if (data.status === 200) {
              router.push('/')
            }
          })
          .catch(({message}) => {
            console.log("err : ", message);
          });
      }
    }
  }
</script>

<style scoped>

</style>
