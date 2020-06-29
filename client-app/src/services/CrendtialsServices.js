const {
  VUE_APP_CLIENT_ID, VUE_APP_CLIENT_SECRET, VUE_APP_REDIRECT_URI, VUE_APP_SCOPE, VUE_APP_GRANT_TYPE
} = process.env;

export const clientId = VUE_APP_CLIENT_ID;
export const clientSecret = VUE_APP_CLIENT_SECRET;
export const redirectUri = VUE_APP_REDIRECT_URI;
export const scope = VUE_APP_SCOPE;
export const grantType = VUE_APP_GRANT_TYPE;
