package priv.wenhao.base.util;

import org.junit.Test;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;

/**
 * RsaUtil Tester.
 *
 * @author yu wenhao
 * @version 1.0
 * @since <pre>12/17/2019</pre>
 */

public class RsaUtilTest {
	/**
	 * Method: genKeyPair(int keyLength)
	 */
	@Test
	public void testGenKeyPair() throws Exception {
		String data = "qmb0jib4bvt4jn6q";
		KeyPair keyPair = RsaUtil.genKeyPair(1024);
//		获取公钥,并以base64格式打印出来
		PublicKey publicKey = keyPair.getPublic();
		System.out.println("public_key=" + new String(Base64.getEncoder().encode(publicKey.getEncoded())));

//		获取私钥,并以base64格式打印出来
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("private_key=" + new String(Base64.getEncoder().encode(privateKey.getEncoded())));

//		公钥加密
		byte[] encryptedBytes = RsaUtil.encrypt(data.getBytes(), publicKey);
		System.out.println("加密后" + new String(encryptedBytes));

//		私钥解密
		byte[] decryptedBytes=RsaUtil.decrypt(encryptedBytes,privateKey);
		System.out.println("解密后:"+new String(decryptedBytes));

//		HashMap<String,String> map=new HashMap<>();
//		map.put("public_key",new String(Base64.getEncoder().encode(publicKey.getEncoded())));
//		map.put("private_key",new String(Base64.getEncoder().encode(privateKey.getEncoded())));
////		将公钥和私钥自动写入到文件中
//		PropertiesUtil.writePropertiesMap(map,"rsa.properties","这是一个rsa的公钥密钥文件");
	}



} 
