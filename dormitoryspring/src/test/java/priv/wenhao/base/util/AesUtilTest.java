package priv.wenhao.base.util;

import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * AesUtil Tester.
 *
 * @author yu wenhao
 * @version 1.0
 * @since <pre>12/23/2019</pre>
 */

public class AesUtilTest {

	/**
	 * Method: encrypt(String content)
	 */
	@Test
	public void testEncrypt() throws Exception {

		String key="1234567890123456";
		String body="123456";
//TODO: Test goes here...
//		Map map = new HashMap<String, String>();
//		map.put("account", "173067113");
//		map.put("password", "123456");
////		map.put("message", "shuai");
//		String content = JSONObject.toJSONString(map);

//		System.out.println("加密前:" + content);

		String encrypt = AesUtil.encrypt(body,key);
		System.out.println("加密后:" + encrypt);

		String decrypt = AesUtil.decrypt(encrypt,key);
		System.out.println("解密后:" + decrypt);

	}

	/**
	 * Method: decrypt(String encryptStr)
	 */
	@Test
	public void testDecrypt() throws Exception {
//TODO: Test goes here... 
	}


	/***
	* ClassName:AesUtilTest
	* Description:实现产生随机的aes并使用rsa加密
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2019/12/31
	*/
	@Test
	public void testhappen() throws Exception {
		String id="173067113";

		String toekn="1050e184168e4bfaaa53759f314ef179";

		String possible = "01234567890asdfghjklqwertyuiopzxcvbnm";
		StringBuilder aes=new StringBuilder();
		Random random=new Random();
		for (int i = 0; i < 16; i++) {
//			text += possible.charAt(Math.floor(Math.random() * possible.length))
			aes.append(possible.charAt(random.nextInt(possible.length())));
		}
		System.out.println(aes.toString());
		System.out.println("--------");
		util(id);
		System.out.println("token---------");
		util(toekn);



	}
	private void util(String text) throws Exception {
		//		公钥加密
		byte[] encryptedBytes = RsaUtil.encrypt(text.getBytes(), RsaUtil.publicKey);
		String baseEncry= Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("加密后" + baseEncry);

//		私钥解密
		byte[] base64Decrypt=Base64.getDecoder().decode(baseEncry);
		byte[] decryptedBytes=RsaUtil.decrypt(base64Decrypt,RsaUtil.privateKey);
		System.out.println("解密后:"+new String(decryptedBytes));
	}

} 
