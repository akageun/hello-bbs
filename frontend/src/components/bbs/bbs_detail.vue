<template>
  <div>
    <gnb/>

    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <h1 id="page_title" class="text-center">

          </h1>
        </div>
      </div>
      <hr>
      <div class="row">
        <div class="col">
          <textarea id="demo1"></textarea>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'
  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_detail",
    components: {
      gnb
    },
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
          element: document.getElementById("demo1"),
          spellChecker: false,
          toolbar: false,
        });

        const articleId = this.$route.params.articleId;

        this.$store.dispatch('GET_BBS', {articleId})
          .then((data) => {
            console.log(data.data.data);

            document.getElementById('page_title').innerText = data.data.data.title;

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
