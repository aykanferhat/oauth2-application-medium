<template>
  <div class="container">
    <!-- Just an image -->
    <nav class="navbar navbar-light bg-light">
      <h3>Products</h3>
    </nav>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Description</th>
        <th scope="col">Price</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(p, i) in ps" :key="p.id">
        <th scope="row">{{i}}</th>
        <td>{{p.title}}</td>
        <td>{{p.description}}</td>
        <td>10.0</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

  import {mapActions, mapState} from 'vuex';

  export default {
    name: 'products',
    computed: {
      ...mapState('product', ['products']),
      ps() {
        return this.products;
      }
    },
    async created() {
      this.getProducts()
    },
    methods: {
      ...mapActions('product', ['getProducts'])
    }
  }

</script>
