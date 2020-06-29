import Vue from 'vue';
import Router from 'vue-router';
import routes from './router';
import TokenService from "../services/TokenService";

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes,
});


router.beforeEach((to, from, next) => {
  const isPublic = to.matched.some(record => record.meta.isPublic);
  const loggedIn = !!TokenService.getToken();

  if (!isPublic && !loggedIn) {
    return next({
      path: '/',
      query: {redirect: to.fullPath},
    });
  }
  next()
});


export default router;
