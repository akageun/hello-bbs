<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <input type="text" id="title" class="form-control mb-3" placeholder="제목을 입력해주세요."/>
          <textarea id="bbsDetail"></textarea>
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
  </div>
</template>

<script>
  import router from '@/router'
  import gnb from '@/components/layouts/gnb'
  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_modify",
    components: {gnb},
    data() {
      return {
        simpleMde: null,
        articleId: null
    }
    },
    mounted() {
      this.simpleMde = new SimpleMDE({
        element: document.getElementById("bbsDetail"),
        spellChecker: false,
      });

      this.articleId = this.$route.params.articleId;

      const articleId = this.articleId;

      this.$store.dispatch('GET_BBS', {articleId})
        .then((data) => {
          document.getElementById('title').value = data.data.data.title;
          // document.getElementById('page_created_user_id').innerText = data.data.data.createdUserId;
          // document.getElementById('page_created_at').innerText = data.data.data.createdAt;

          this.simpleMde.value(data.data.data.content);
        })
        .catch(({message}) => {
          console.log("err : ", message);
        });
    },
    methods: {
      saveData() {
        const title = document.getElementById('title').value;
        const content = this.simpleMde.value();
        const statusCd = "NORMAL";

        const articleId = this.articleId;

        this.$store.dispatch('MODIFY_BBS_ARTICLE', {articleId, title, content, statusCd})
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
