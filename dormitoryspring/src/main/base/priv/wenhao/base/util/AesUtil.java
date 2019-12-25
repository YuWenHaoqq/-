package priv.wenhao.base.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Description: aes的工具类
 * Author: yuWenHao
 * Date: 2019/12/23
 */

public class AesUtil {
//	public static final String KEY = "q7zq7qn8k3myu9n4";

	//	参数代表
	private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
	//		密钥加密生成器
	private static KeyGenerator keyGenerator;
	//	加密算法??
	private static Cipher cipher;

	static {
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			cipher = Cipher.getInstance(ALGORITHMSTR);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Description:Aes加密函数
	 * param:[content, encryptKey]
	 * return:java.lang.String
	 * Author:yu wenhao
	 * date:2019/12/23
	 */
	public static String encrypt(String content,String key) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
		byte[] bytes = cipher.doFinal(content.getBytes("utf-8"));
//		采用base64算法进行转码,避免出现中文乱码
		return Base64.encodeBase64String(bytes);
	}

	/***
	 * Description:Aes解密函数
	 * param:[encryptStr]
	 * return:java.lang.String
	 * Author:yu wenhao
	 * date:2019/12/23
	 */
	public static String decrypt(String encryptStr,String key) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
//		采用base64算法进行转码,避免出现中文乱码
		byte[] encryptBytes = Base64.decodeBase64(encryptStr);
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		return new String(decryptBytes);
	}
}
