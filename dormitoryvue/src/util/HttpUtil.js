import axios from 'axios'
import {Loading} from "element-ui";
import router from '@/router'
import {aesMapDecrypt, aesValue} from "@/util/AESUtil";
import {getRSAKey, rsaEncryption} from "@/util/RSAUtil";

let loadingInstance = null

const instance = axios.create({
//    设置超时时间
    timeout: 10000,
    headers: {}
})

// 统一设置post请求头
instance.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8 '
// instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

//添加请求拦截器
instance.interceptors.request.use(config => {
        config.headers['token'] = rsaEncryption(sessionStorage.getItem('token')) || ''
        config.headers['id'] = rsaEncryption(sessionStorage.getItem('stuId'))
            || rsaEncryption(sessionStorage.getItem('teaId'))||''
        config.headers['aes'] = rsaEncryption(sessionStorage.getItem("aes")) || ''
        loadingInstance = Loading.service({
            spinner: 'el-icon-loading',
            text: '玩命加载中'
        })
        return config
    },
    error => {
        //对请求错误做什么
        return Promise.reject(error)
    })
//添加响应拦截器
instance.interceptors.response.use(res => {
        loadingInstance.close()
        if (res.data.code === 0) {
            return Promise.resolve(res.data)
        } else if (res.data.code === 4) {
            router.push({
                path: '/404'
            })
            return Promise.reject(res.data)
        } else {
            return Promise.reject(res.data)
        }
    },
    error => {
        loadingInstance.close()
        return Promise.reject(new Error(error.message))
    })

// 统一封装get请求
export function get(url, params, config = {}) {
    return instance({
        method: 'get',
        url: url,
        params: params,
        ...config
    }).then(response => {
        return Promise.resolve(response)
    }).catch(error => {
        return Promise.reject(error)
    })
}

// 统一封装post请求,使用aes加密
export function aesPost(url, data, config = {}) {
    getRSAKey()
    return instance({
        method: 'post',
        url: url,
        data: aesValue(data),
        ...config
    }).then(res => {
        // window.console.log(res.data)
        aesMapDecrypt(res.data)
        return Promise.resolve(res)
    }).catch(err => {
        return Promise.reject(err)
    })
}

//统一封装post请求
export function post(url, data, config = {}) {
    return instance({
        method: 'post',
        url: url,
        data: data,
        ...config
    }).then(res => {
        return Promise.resolve(res)
    }).catch(err => {
        return Promise.reject(err)
    })

}
//统一封装post请求,参数使用param
export function paramsPost(url, params, config = {}) {
    return instance({
        method: 'post',
        url: url,
        params: params,
        ...config
    }).then(res => {
        return Promise.resolve(res)
    }).catch(err => {
        return Promise.reject(err)
    })

}

