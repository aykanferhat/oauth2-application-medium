import TokenService from './TokenService';

class Interceptor {
  constructor(axiosObject) {
    this.requestInterceptor = null;
    this.responseInterceptor = null;
    this.axiosObject = axiosObject;
  }

  setRequestInterceptor() {
    this.requestInterceptor = this.axiosObject.interceptors.request.use((config) => {
      const authToken = TokenService.getToken();

      if (authToken) {
        config.headers.common.Authorization = `Bearer ${authToken}`;
      }
      return config;
    }, error => Promise.reject(error));
  }

  setResponseInterceptor() {
    this.responseInterceptor = this.axiosObject.interceptors.response.use(
      response => response,
      ({response}) => {
        return Promise.reject(response);
      },
    );
  }

  removeRequestInterceptor() {
    this.axiosObject.interceptors.request.eject(this.requestInterceptor);
  }

  removeResponseInterceptor() {
    this.axiosObject.interceptors.response.eject(this.responseInterceptor);
  }
}

export default Interceptor
