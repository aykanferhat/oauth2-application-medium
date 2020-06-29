import HttpService from './HttpService';

const {
  VUE_APP_PRODUCT_API_ENDPOINT, VUE_APP_AUTH_API_ENDPOINT
} = process.env;

export const productApi = new HttpService({baseURL: VUE_APP_PRODUCT_API_ENDPOINT});
export const authApi = new HttpService({baseURL: VUE_APP_AUTH_API_ENDPOINT});

