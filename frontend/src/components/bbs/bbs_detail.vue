<template>
  <div>
    <gnb/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <h1 id="page_title" style="border-bottom: 1px solid gold; padding-bottom: 5px;">{{page_title}}</h1>
        </div>
        <div class="col-12 text-right">
          <span id="page_created_user_id" style="margin-right: 5px; font-weight: bolder;">
            {{create_user_id}}
          </span>
          <span id="page_created_at">
            {{page_created_at}}
          </span>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <textarea id="bbsDetail"></textarea>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import gnb from 'SrcRoot/components/layouts/gnb'
  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_detail",
    components: {gnb},
    data() {
      return {
        simpleMde: null,
        page_title: '',
        create_user_id: '',
        page_created_at: ''
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
            this.page_title = data.data.data.title;
            this.create_user_id = data.data.data.createdUserId;
            this.page_created_at = data.data.data.createdAt;
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
