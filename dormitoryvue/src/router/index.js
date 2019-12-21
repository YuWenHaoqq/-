import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'index',
            component: resolve => require(['@/view/login/Login'], resolve)
        }, {
            path: '/test',
            name: 'test',
            component: resolve => require(['@/components/LoginView'], resolve)
        },
        {
            path: '*',
            name: '404',
            component: resolve => require(['@/view/404/404'], resolve)
        }
    ]
})
