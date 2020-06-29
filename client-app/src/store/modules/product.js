import {productApi} from "../../services/ApiServices";

export default {
  namespaced: true,
  state: {
    products: []
  },
  actions: {
    async getProducts({commit}) {
      return await productApi.get("/products")
        .then(response => {
          return commit('setProducts', response);
        });
    }
  },
  mutations: {
    setProducts(state, products) {
      state.products = products
    },
  },
}
