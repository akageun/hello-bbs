<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <h1 id="page_title" style="border-bottom: 1px solid gold; padding-bottom: 5px;"></h1>
      </div>
      <div class="col-12 text-right">
        <span id="page_created_user_id" style="margin-right: 5px; font-weight: bolder;"></span>
        <span id="page_created_at"></span>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <textarea id="bbsDetail"></textarea>
      </div>
    </div>
  </div>
</template>

<script>
  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_detail",
    data() {
      return {
        simpleMde: null
      }
    },
    mounted() {

      this.getBbsData();

    },
    methods: {
      getBbsData: function () {
        this.simpleMde = new SimpleMDE({
          element: document.getElementById("bbsDetail"),
          spellChecker: false,
          toolbar: false,
        });

        const articleId = this.$route.params.articleId;

        this.$store.dispatch('GET_BBS', {articleId})
          .then((data) => {
            document.getElementById('page_title').innerText = data.data.data.title;
            document.getElementById('page_created_user_id').innerText = data.data.data.createdUserId;
            document.getElementById('page_created_at').innerText = data.data.data.createdAt;

            this.simpleMde.value(data.data.data.content);
            this.simpleMde.togglePreview();
          })
          .catch(({message}) => {
            console.log("err : ", message);
          });

      }
    }
  }
</script>

<style>
  .CodeMirror {
    border: none;
  }

  .editor-preview {
    background: #FFFFFF;
  }
</style>
<style scoped>

</style>
