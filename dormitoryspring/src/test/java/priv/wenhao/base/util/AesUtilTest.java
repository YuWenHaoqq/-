package priv.wenhao.base.util;

import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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


} 
