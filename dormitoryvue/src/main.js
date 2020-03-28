import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueRouter from "vue-router";
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vuex from 'vuex'
// import SvgIcon from '@/icons/svg'
// import 'lib-flexible'

Vue.config.productionTip = false
// Vue.component('svg-icon',SvgIcon)
Vue.use(VueRouter)
Vue.use(VueAxios,axios);
Vue.use(ElementUI)
Vue.use(Vuex)
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
