package priv.wenhao.base.util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;

/**
 * Description: RsaUtil
 * Author: yuWenHao
 * Date: 2019/12/17
 */

public class RsaUtil {
	public static PrivateKey privateKey;
	public static PublicKey publicKey;

	static {
		HashMap<String, String> hashMap = PropertiesUtil.getPropertiesMap("rsa.properties");
		try {
			privateKey = getPrivateKey(hashMap.get("private_key"));
			publicKey = getPublicKey(hashMap.get("public_key"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Description:生成密钥对
	 * param:[keyLength]
	 * return:java.security.KeyPair
	 * Author:yu wenhao
	 * date:2019/12/4
	 */
	public static KeyPair genKeyPair(int keyLength) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keyLength);
		return keyPairGenerator.generateKeyPair();
	}

	/***
	 * Description:公钥加密
	 * param:[content, publicKey]
	 * return:byte[]
	 * Author:yu wenhao
	 * date:2019/12/4
	 */
	public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
//		java默认"RSA"="RSA/ECB/PKCS1Padding"
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}

	/***
	 * Description:私钥解密
	 * param:[content, privateKey]
	 * return:byte[]
	 * Author:yu wenhao
	 * date:2019/12/4
	 */
	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}

	/***
	 * Description:将base64编码后的公钥字符串转成PublicKey实例
	 * param:[publicKey]
	 * return:java.security.PublicKey
	 * Author:yu wenhao
	 * date:2019/12/4
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}

	/***
	 * Description:将base64编码后的私钥字符串转成PublicKey实例
	 * param:[privateKey]
	 * return:java.security.PrivateKey
	 * Author:yu wenhao
	 * date:2019/12/4
	 */
	public static PrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	public static String decryptHeader(String text) throws Exception {
		return new String(RsaUtil.decrypt(Base64.getDecoder().decode(text),RsaUtil.privateKey));
	}

}
