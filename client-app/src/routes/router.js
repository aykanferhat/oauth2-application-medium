import Products from "../pages/Products";
import Home from "../pages/Home";
import Token from "../pages/Token";
import Profile from "../pages/Profile";

export default [
  {
    path: '/',
    name: 'home',
    component: Home,
    meta: {
      isPublic: true
    },
  },
  {
    path: '/token',
    name: 'token',
    component: Token,
    meta: {
      isPublic: true
    },
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/products',
    name: 'products',
    component: Products
  },
];
