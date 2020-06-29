<template>
  <div class="container">
    <nav class="navbar navbar-light bg-light">
      <div class="col-md-1">
        <span class="navbar-brand mb-0 h1">Home</span>
      </div>
      <div class="col-md-11 align-content-end">
        <a class="navbar-brand mb-0 h1 btn-success btn-lg align-content-lg-end"
           href="http://localhost:9999/oauth/authorize?response_type=code&client_id=clientX&redirect_uri=http://localhost:8080/token&scope=read"
        >Login</a>
      </div>
    </nav>
  </div>
</template>

<script>

  export default {
    name: 'home'
  }

</script>
