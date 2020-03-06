import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'login',
            component: resolve => require(['@/view/login/Login'], resolve)
        }, {
            path: '/test',
            name: 'test',
            component: resolve => require(['@/components/NoticeTemplate'], resolve)
        },
        {
            path:'/tt',
            name:'tttt',
            component: resolve => require(['@/view/test/Test'], resolve)
        },
        {
            path: '*',
            name: '404',
            component: resolve => require(['@/view/404/404'], resolve)
        },
        {
            path: '/index',
            name: 'page',
            component: resolve => require(['@/view/index/index'], resolve),
            children:[
                {
                    path: 'homepage',
                    name: 'page',
                    component: resolve => require(['@/view/homepage/HomePage'], resolve)
                },
                {
                    path: 'stuSignin',
                    name: 'signin',
                    component: resolve => require(['@/view/stu/SignIn'], resolve)
                }
            ]
        }
    ]
})
