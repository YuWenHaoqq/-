import CryptoJS from 'crypto-js'
import {myLocalStorage} from "@/util/MyLocalStorage";


/***
 *@Description:ecb模式-加密
 *Author:yu wenhao
 *@date:2019/12/23
 */
export function AseEncryption(text) {
    // let key=CryptoJS.enc.Utf8.parse(getResKey())
    // // let srcs=CryptoJS.enc.Utf8.parse(text)
    // let encryptedData=CryptoJS.AES.encrypt(text,key,{
    //     mode:CryptoJS.mode.CBC,
    //     padding:CryptoJS.pad.Pkcs7
    // })
    // return encryptedData.ciphertext.toString();

    let key=CryptoJS.enc.Utf8.parse(getResKey())
    let encryptedData = CryptoJS.AES.encrypt(text, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    // window.console.log("加密前："+text);
    // window.console.log("加密后："+encryptedData);
    return encryptedData
}
/***
 *@Description:解密
 *Author:yu wenhao
 *@date:2019/12/24
*/
export function decrypt(text) {
    let key=CryptoJS.enc.Utf8.parse(getResKey())
    // let encryptedData = text.ciphertext.toString();
    //
    //
    //
    // let encryptedHexStr=CryptoJS.enc.Hex.parse(encryptedData.toString())
    // let encryptedBase64Str=CryptoJS.enc.Base64.stringify(encryptedHexStr)
    //
    // let decryptedData=CryptoJS.AES.decrypt(encryptedBase64Str,key,{
    //     mode:CryptoJS.mode.CBC,
    //     padding:CryptoJS.pad.Pkcs7
    // })
    // let decryptedStr=decryptedData.toString(CryptoJS.enc.Utf8)
    // window.console.log(decryptedStr)
    // return decryptedStr.toString();
    let encryptedData = text.ciphertext.toString();
    let encryptedHexStr = CryptoJS.enc.Hex.parse(encryptedData);
    let encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);

    let decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    let decryptedStr = decryptedData.toString(CryptoJS.enc.Utf8);
    // window.console.log("解密后:"+decryptedStr);
    return decryptedStr;
}

export function getResKey() {
    // window.console.log(myLocalStorage.Cache.get("aes")===null)
    // window.console.log(myLocalStorage.Cache.get("aes")==="")
    if (myLocalStorage.Cache.get("aes")===null ||myLocalStorage.Cache.get("aes")===""){
        myLocalStorage.Cache.put("aes",randomKey())
        // window.console.log("调用")
    }
    return myLocalStorage.Cache.get("aes");

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
    // Object.keys(obj).forEach(function (key) {
    //     window.console.log("--",obj[key])
    // })
    return obj
}
