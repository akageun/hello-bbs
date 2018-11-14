<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <input type="text" class="form-control mb-3" placeholder="제목을 입력해주세요." />
          <textarea id="demo2">
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
    name: "bbs_write",
    components: {
      gnb
    },
    mounted() {
      new SimpleMDE({
        element: document.getElementById("demo2"),
        spellChecker: false,
      });

    }
  }
</script>

<style scoped>

</style>
