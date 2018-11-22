<template>
  <div>
    <gnb/>
    <div class="container-fluid">
      <div class="row">
        <div class="col">
          <input type="text" id="title" class="form-control mb-3" placeholder="제목을 입력해주세요."/>
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
  </div>
</template>

<script>
  import gnb from '@/components/layouts/gnb'
  import router from '@/router'

  import SimpleMDE from 'simplemde';
  import 'simplemde/dist/simplemde.min.css'

  export default {
    name: "bbs_write",
    data() {
      return {
        simpleMde: null
      }
    },
    components: {gnb},
    mounted() {
      this.simpleMde = new SimpleMDE({
        element: document.getElementById("bbs_write_area"),
        spellChecker: false,
      });

    },
    methods: {
      saveData() {
        const title = document.getElementById('title').value;
        const content = this.simpleMde.value();
        const statusCd = "NORMAL";

        this.$store.dispatch('ADD_BBS_ARTICLE', {title, content, statusCd})
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
