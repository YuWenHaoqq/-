<template>
    <div>
        <button @click="aaa">123</button>
    </div>
</template>

<script>
    // import { rsaEncryption} from "@/util/RsaUtil";
    import CryptoJS from 'crypto-js'
    import {getAesKey} from "@/util/AESUtil";

    export default {
        name: "",
        methods:{
            a12(){
                // rsaEncryption("23")
            },

            aaa(){
                let key = CryptoJS.enc.Utf8.parse(getAesKey());

                var plaintText = 'ABCDEFGH'; // 明文

                var encryptedData = CryptoJS.AES.encrypt(plaintText, key, {
                    mode: CryptoJS.mode.ECB,
                    padding: CryptoJS.pad.Pkcs7
                });

                window.console.log("加密前："+plaintText);
                window.console.log("加密后："+encryptedData);

                window.console.log("type:",typeof encryptedData)

                // encryptedData = encryptedData.ciphertext.toString();
                // window.console.log("type2:",typeof encryptedData)



                let decrypt=CryptoJS.AES.decrypt("szibpkSa/aM3GHlIodfJiw==",key,{mode: CryptoJS.mode.ECB,
                    padding: CryptoJS.pad.Pkcs7})
                window.console.log(CryptoJS.enc.Utf8.stringify(decrypt).toString())


                // var encryptedHexStr = CryptoJS.enc.Hex.parse("szibpkSa/aM3GHlIodfJiw==");
                // var encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);
                //
                // var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
                //     mode: CryptoJS.mode.ECB,
                //     padding: CryptoJS.pad.Pkcs7
                // });
                //
                // var decryptedStr = decryptedData.toString(CryptoJS.enc.Utf8);
                //
                // window.console.log("解密后:"+decryptedStr);


                // var pwd = "PCsUFtgog9/qpqmqXsuCRQ==";
                //加密服务端返回的数据
                // var decryptedData = CryptoJS.AES.decrypt(pwd, key, {
                //     mode: CryptoJS.mode.ECB,
                //     padding: CryptoJS.pad.Pkcs7
                // });
                //
                // window.console.log("解密服务端返回的数据:"+decryptedStr);

            }
        }
    }
</script>

<style scoped>

</style>
