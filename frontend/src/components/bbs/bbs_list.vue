<template>
  <div>
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
      <div class="row">
        <div class="col">
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
  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'
  /*https://github.com/sparksuite/simplemde-markdown-editor*/

  export default {
    name: "bbs_list",
    mounted() {
      const simplemde = new SimpleMDE({
        element: document.getElementById("demo1"),
        spellChecker: false,
        toolbar: false,
      });

      simplemde.togglePreview();

      new SimpleMDE({
        element: document.getElementById("demo2"),
        spellChecker: false,
      });

    }
  }
</script>

<style scoped>

</style>
