import {authApi} from "../../services/ApiServices";
import TokenService from "../../services/TokenService";
import router from "../../routes";

import {clientId, clientSecret, redirectUri, grantType} from "../../services/CrendtialsServices";


const qs = require('querystring');

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
    },

    async getTokenWithCode({dispatch}, code) {
      try {
        if (code == null) {
          router.push({name: 'home'});
          return
        }
        const encoded = btoa(`${clientId}:${clientSecret}`);
        const headers = {
          'Content-Type': 'application/x-www-form-urlencoded',
          Authorization: `Basic ${encoded}`,
        };
        const data = {
          code,
          grant_type: grantType,
          redirect_uri: redirectUri,
        };
        await authApi.post("/oauth/token", qs.stringify(data), {headers})
          .then(response => {
            TokenService.saveToken(response.access_token);
          });
      } catch (error) {
        throw error;
      }
    },

    async login({dispatch}, {username, password}) {
      try {
        const data = {
          username,
          password,
          scope: 'openid',
          grant_type: 'password',
        };
        const encoded = btoa(`${clientId}:${clientSecret}`);
        const headers = {
          'Content-Type': 'application/x-www-form-urlencoded',
          Authorization: `Basic ${encoded}`,
        };
        const response = await authApi.post('/oauth/token', qs.stringify(data), {headers});
        TokenService.saveToken(response.access_token);
      } catch (error) {
        throw error;
      }
    },
  },
  mutations: {
    setProducts(state, products) {
      state.products = products
    },
  },
}
