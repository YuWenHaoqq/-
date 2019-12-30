package priv.wenhao.base.util;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import priv.wenhao.base.pojo.dto.IPRequestDto;

import java.io.UnsupportedEncodingException;

/**
 * Description: AddressUtil
 * Author: yuWenHao
 * Date: 2019/12/30
 */

@Slf4j
public class AddressUtil {
	public static IPRequestDto getAddresses(String content, String encodingString) {
//		这里调用pc online的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		String returnStr = HttpRequest.sendPost(urlStr, content, encodingString);
		if (returnStr != null) {
//			处理返回的省市区信息
			Gson gson = new Gson();
			return gson.fromJson(returnStr, IPRequestDto.class);
		}
		return new IPRequestDto();
	}

}
