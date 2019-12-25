import{get} from "@/util/HttpUtil";
import {Message} from "element-ui";
import NodeRsa from 'node-rsa'

export function getRsaKey() {
    get('/api/encryption/rsa').then(res=>{
        window.console.log(res.code)
        sessionStorage.setItem("rsa",res.data)
    }).catch(function (err) {
        Message.error(err.message)
    })

}

export function rsaEncryption(text,encoding="base64",source_encoding ='utf-8') {
    let key=new NodeRsa()
    let publicKey='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIETIuhh1HcZSNNDSX0p7rRTUdE7JmWrLxsVJFJYh61YSCRB9xKaWnwdkikbFS385uUZC17UARpcTDpfCsFRLPJT90yMlGzkvKZz8ae71SJVgv5XQXpXxNiSI20bN/u5H4h3ucbjQM4MCY7KTfGWnyhEfjNlqLHqBVQMAVf3PJRwIDAQAB'
    key.setOptions({
        encryptionScheme:"pkcs1"
    })
    key.importKey(publicKey,"pkcs8-public-pem")
//    加密并返回加密结果
    return key.encrypt(text,encoding,source_encoding)
}
