const requireModule = require.context('./', true, /.js$/);

const modules = {
  namespaced: true,
  modules: {},
};

requireModule.keys().forEach((filePath) => {
  // eslint-disable-next-line prefer-const
  let [, domain, fileName] = filePath.split('/');
  fileName = fileName || domain;
  // Don't register this file as a Vuex module
  if (fileName.includes('index.js')) return;

  const moduleName = fileName.replace(/(\.\/|\.js)/g, '');
  modules[moduleName] = {
    namespaced: true,
    ...requireModule(filePath).default,
  };
});

export default modules;
