import CryptoJS from 'crypto-js'


/***
 *@Description:ecb模式-加密
 *Author:yu wenhao
 *@date:2019/12/28
*/
export function AseEncryption(text) {
    let key=CryptoJS.enc.Utf8.parse(getAesKey())
    let encryptedData = CryptoJS.AES.encrypt(text, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encryptedData
}
/***
 *@Description:解密
 *Author:yu wenhao
 *@date:2019/12/24
 */
export function decrypt(text) {
    let key=CryptoJS.enc.Utf8.parse(getAesKey())
    let decrypt=CryptoJS.AES.decrypt(text,key,{mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7})
    // window.console.log(CryptoJS.enc.Utf8.stringify(decrypt).toString())
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}

export function getAesKey() {
    if (sessionStorage.getItem("aes")===null ||sessionStorage.getItem("aes")===""){
        sessionStorage.setItem("aes",randomKey())
    }
    return sessionStorage.getItem("aes")

}

function randomKey() {
    let text = ""
    let possible = "01234567890asdfghjklqwertyuiopzxcvbnm"
    for (let i = 0; i < 16; i++) {
        text += possible.charAt(Math.floor(Math.random() * possible.length))
    }
    return text
}

export function aesValue(map) {
    let obj=new Object()
    Object.keys(map).forEach(function (key) {
        eval("obj."+key+"='"+AseEncryption(map[key])+"'")
    })
    return obj
}

export function aesMapDecrypt(map) {
    for (let key in map){
        map[key]=decrypt(map[key])
    }
}

