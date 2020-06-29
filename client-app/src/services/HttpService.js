import axios from 'axios';

import Interceptor from './InterceptorService';
import TokenService from './TokenService';

class HttpService {
  constructor(options) {
    this.instance = axios.create(options);
    this.interceptor = new Interceptor(this.instance);
    this.interceptor.setRequestInterceptor();
    this.interceptor.setResponseInterceptor();
  }

  async get(url, config) {
    const {data} = await this.instance.get(url, config);
    return data;
  }

  async post(url, payload, config) {
    const {data} = await this.instance.post(url, payload, config);
    return data;
  }

  async put(url, payload, config) {
    const {data} = await this.instance.put(url, payload, config);
    return data;
  }

  async delete(url, config) {
    const {data} = await this.instance.delete(url, config);
    return data;
  }

  postWithSyncXHR(url, payload) {
    const request = new XMLHttpRequest();
    const AUTH_TOKEN = TokenService.getToken();
    const endpoint = `${this.instance.defaults.baseURL}${url}`;

    request.open('POST', endpoint, false);
    request.setRequestHeader('Authorization', `Bearer ${AUTH_TOKEN}`);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

    request.send(
      JSON.stringify(payload),
    );
  }
}


export default HttpService;
