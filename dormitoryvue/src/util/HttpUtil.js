/***
 *@Description:封装axios的get,post方法
 *Author:yu wenhao
 *@date:2019/12/20
 */
import axios from 'axios'
import {Loading, Message} from "element-ui";
import router from '@/router'

// 加载全局的loading
let loadingInstance = null

const instance = axios.create({
//    创建axios实例,在这里设置请求的默认配置
//    设置超过时间10s
    timeout: 1000,
})
// 文档中的统一设置post请求头.下面会说到post请求的的几种'Content-Type'
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

let httpCode = {
    400: '请求参数错误',
    401: '权限不足, 请重新登录',
    403: '服务器拒绝本次访问',
    404: '请求资源未找到',
    500: '内部服务器错误',
    501: '服务器不支持该请求中使用的方法',
    502: '网关错误',
    504: '网关超时'
}

// 添加请求拦截器
instance.interceptors.request.use(config => {
        config.headers['token'] = sessionStorage.getItem('token') || ''
        loadingInstance = Loading.service({
            //    发起请求时加载全局loading,请求失败或有响应时会有关闭
            spinner: 'el-icon-loading',
            text: '玩命加载中...'
        })
// //    在这里:可以根据业务需求可以在发送请求之前做些什么:例如我这个是导出文件的接口,因为返回的是二进制流,所以需要设置请求响应类型为blob,就可以在此处设置
//         if (config.url.includes('netword/export')) {
//             config.headers['responseType'] = 'blob'
//         }
// //    我这里是文件上传,发送的是二进制流,所以需要设置请求头'Content-Type'
//         if (config.url.includes('file/upload')) {
//             config.headers['Content-Type'] = 'multipart/form-data'
//         }
        return config;
    },
    error => {
        // 对请求错误做什么
        return Promise.reject(error)
    }
)

// 添加响应拦截器
instance.interceptors.response.use(response => {
        loadingInstance.close()
        if (response.data.code === 0) {
            return Promise.resolve(response.data)
        } else if (response.data.code === 4) {
            //    在token失效的时候跳转回到登录页面
            Message({
                message: response.data.message,
                type: "error"
            })
            router.push({
                path: '/'
            })
        } else {
            Message({
                message: response.data.message,
                type: "error"
            })
            return Promise.reject(response.data.message)
        }
    },
    error => {
        loadingInstance.close()
        //    根据请求失败的http状态码去给用户响应的提示
        let tips = error.status in httpCode ? httpCode[error.status] : error.message
        Message({
            message: tips,
            type: "error"
        })
        return Promise.reject(new Error('请求超时,请刷新重试'))
    })

// 统一封装get请求
export const get = (url, params, config = {}) => {
    return instance({
        method: 'get',
        url: url,
        params: params,
        ...config
    }).then(response => {
        return Promise.resolve(response.data)
    }).catch(error => {
        return Promise.reject(error)
    })
}

//  或者写成下面的这样:Promise.resolve()和Promise.reject()返回的是promise对象,二者都是语法糖
export const post = (url, data, config = {}) => {
    return instance({
        method: 'post',
        url: url,
        data,
        ...config
    }).then(response => {
        return Promise.resolve(response.data)
    }).catch(error => {
        return Promise.reject(error)
    })
}


// 作者：Plz叫我红领巾
// 链接：https://juejin.im/post/5d2f1c54e51d454f6f16eca9
//     来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
