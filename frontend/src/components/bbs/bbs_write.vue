<template>
  <div>
    <gnb/>
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
          <br>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'
  import router from '@/router'
  import $ from 'jquery'

  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_write",
    components: {gnb},
    data() {
      return {
        simpleMde: null
      }
    },
    mounted() {
      this.simpleMde = new SimpleMDE({
        element: document.getElementById("bbs_write_area"),
        spellChecker: false,
      });

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
    },
    methods: {
      saveData() {
        const title = document.getElementById('title').value;
        const content = this.simpleMde.value();
        const categoryId = document.getElementById('category_id').value;
        const statusCd = "NORMAL";

        this.$store.dispatch('ADD_BBS_ARTICLE', {title, content, statusCd, categoryId})
          .then((data) => {
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
