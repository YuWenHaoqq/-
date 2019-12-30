import axios from 'axios'
import {Message} from "element-ui";
import JSEncrypt from 'jsencrypt'

export function getRSAKey() {
    if (sessionStorage.getItem("rsa") === null || sessionStorage.getItem("rsa") === '') {
        axios.get('/api/encryption/rsa').then(res => {
            sessionStorage.setItem("rsa", res.data.data)
        }).catch(() => {
            Message({
                message: '请求失败,请联系管理员',
                type: "error"
            })
        })
    }
    return sessionStorage.getItem("rsa");
}

export function rsaEncryption(text) {
    //公钥
    let PUBLIC_KEY = getRSAKey();
    //使用公钥加密
    let encrypt = new JSEncrypt();
    encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
    let encrypted = encrypt.encrypt(text);
    return encrypted
}
