const tokenKey = 'auth-service.AUTH_TOKEN';

class TokenService {
  static getToken() {
    return localStorage.getItem(tokenKey);
  }

  static saveToken(token) {
    localStorage.setItem(tokenKey, token);
  }

  static removeToken() {
    localStorage.removeItem(tokenKey);
  }
}

export default TokenService;
