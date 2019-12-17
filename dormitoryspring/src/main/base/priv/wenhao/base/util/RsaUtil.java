package priv.wenhao.base.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaUtil {
	public static PrivateKey privateKey;
	public static PublicKey publicKey;

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

}
