import JSEncrypt from 'jsencrypt'
import {get} from "@/util/HttpUtil";
import {Message} from "element-ui";

export function rsaEncryption(text) {
    let encrypt=new JSEncrypt();
    encrypt.setPublicKey(getRsaKey())
    let ll=encrypt.encrypt(text)

    let ff=new JSEncrypt()
    ff.setPrivateKey('MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMgRMi6GHUdxlI00NJfSnutFNR0TsmZasvGxUkUliHrVhIJEH3EppafB2SKRsVLfzm5RkLXtQBGlxMOl8KwVEs8lP3TIyUbOS8pnPxp7vVIlWC/ldBelfE2JIjbRs3+7kfiHe5xuNAzgwJjspN8ZafKER+M2WoseoFVAwBV/c8lHAgMBAAECgYBZ/RIbRwvIZNgyoza8y2nb3SdEyuVFkKpIeD/PBlDbH0sY5NdXqSnvyQuHtwyRgEVz7ZAvpGVbCrr0XaFhZaEta4wcGmBQhLmkKqEqrOaGNhJoFQQhIHEObRBFvCEfFJPvmWTrdo/MTJVXv3wODRHprrJXVy4O/x0PxutYrHe74QJBAOuj6mV8ZbuhfdBCr4hIA+y4pPK04QG9FThnM9YXbHq1Ej+wIOFlt9itvckKODOJ+H5rcSGFwsSAy9l+P0KY51kCQQDZWnHto819ONIMyZ6syorZzRUoT4wxnUp/4YdmVYMldpzVT+DQrDHfpFovbxy3/JOgxiTG0Lvww1W/mlosYsGfAkEAwfbpaKRfMXWbFKTvzGRLJr5OuKH/XOkB4K3C04Xwe4k0/kvCpQaPsMJDKItK+9GkRJu4QfnS+uFx6xOqiu1F8QJABM8VejaHk1+wdgv38MbCb7Mz5qJr7aUXQuEQh9kYf7myGp4b0UIeEF6oiCgjMIbsFUCNC8EOh3Mq8IRPvm+o7QJBAOGvKARRYP7fC7zej1M5YwC3xaEEUiSQfXGcNa9KJBuu5NCbJmnS/hOgUp9O6pJQQwqqPPg7zTR+oz1Xie0HTPY=')
    window.console.log("加密",ll)
    window.console.log("解密",ff.decrypt(ll))
    return encrypt.encrypt(text);
}

//获得rsa密钥
export function getRsaKey() {
    if (sessionStorage.getItem("rsa")===null){
        get('api/encryption/rsa').then(
            res => {
                sessionStorage.setItem("rsa",res.data)
            }).catch(function () {
            Message.error("请求错误,请刷新重试")
        })
    }
    return sessionStorage.getItem("rsa")


}
