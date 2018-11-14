<template>
  <div>
    <gnb/>

    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <textarea id="demo1">
# Intro
Go ahead, play around with the editor! Be sure to check out **bold** and *italic* styling, or even [links](https://google.com). You can type the Markdown syntax, use the toolbar, or use shortcuts like `cmd-b` or `ctrl-b`.
## Lists
Unordered lists can be started using the toolbar or by typing `* `, `- `, or `+ `. Ordered lists can be started by typing `1. `.

#### Unordered
* Lists are a piece of cake
* They even auto continue as you type
* A double enter will end them
* Tabs and shift-tabs work too

#### Ordered
1. Numbered lists...
2. ...work too!

## What about images?
        </textarea>
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
    mounted() {
      console.log(this.$route.params.articleId);

      this.getBbsData();
    },
    methods: {
      getBbsData: function () {
        new SimpleMDE({
          element: document.getElementById("demo1"),
          spellChecker: false,
          toolbar: false,
        }).togglePreview();
        ;
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
