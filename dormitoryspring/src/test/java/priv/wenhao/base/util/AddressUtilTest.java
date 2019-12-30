package priv.wenhao.base.util;

import org.junit.Test;
import priv.wenhao.base.pojo.dto.IPRequestDto;

import java.io.UnsupportedEncodingException;

/**
 * AddressUtil Tester.
 *
 * @author yu wenhao
 * @version 1.0
 * @since <pre>12/30/2019</pre>
 */

public class AddressUtilTest {

	/**
	 * Method: getAddresses(String content, String encodingString)
	 */
	@Test
	public void testGetAddresses() throws Exception {
//TODO: Test goes here...
		// 测试ip 221.232.245.73 湖北武汉
		String ip = "47.52.163.119";
		IPRequestDto address;
		try {
			address = AddressUtil.getAddresses("ip=" + ip, "utf-8");
			System.out.println(address.getData().getRegion());;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println(address);
		// 输出结果为：中国 湖北省 武汉市
	}
} 
