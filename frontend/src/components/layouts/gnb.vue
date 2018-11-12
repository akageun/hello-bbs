<template>
  <div>
    <header>
      <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-3">
        <a class="navbar-brand" href="#">Hello, BBS</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse"
                aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#">Disabled</a>
            </li>
          </ul>
          <div class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2 form-control-sm" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success  btn-sm my-2 my-sm-0 mr-1">Search</button>
            <button class="btn btn-outline-info btn-sm" @click="openModal();">USER</button>
          </div>
        </div>
      </nav>

    </header>

    <signin/>
    <signup/>
  </div>


</template>

<script>
  import signin from '@/components/user/signin';
  import signup from '@/components/user/signup';

  export default {
    name: "gnb",
    components: {
      signin, signup

    },
    methods: {
      openModal: function () {
        $("#exampleModal").modal();
      }
    }
  }
</script>

<style scoped>

</style>
