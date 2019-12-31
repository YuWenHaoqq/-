package priv.wenhao.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import priv.wenhao.base.exception.BussinessException;

import java.util.Base64;
import java.util.Map;

/**
 * Description: 将httpInputMessage里面传过来map参数进行加解密
 * Author: yuWenHao
 * Date: 2019/12/25
 */

public class HttpInputMessageUtil {
	/***
	* Description:解密
	* param:[body, key]
	* return:java.lang.String
	* Author:yu wenhao
	* date:2019/12/25
	*/
	public static String decryptString(String body,String key) throws Exception {
		String trueKey=RsaUtil.decryptHeader(key);
		if (Strings.isNullOrEmpty(trueKey)||trueKey.length()%16!=0){
			throw new BussinessException(5,"aes密钥错误");
		}

		Map<String,Object> map;
		JSONObject jsonObject=JSONObject.parseObject(body);
		map= JSON.toJavaObject(jsonObject,Map.class);
		System.out.println(trueKey);
		for (Map.Entry<String,Object> entry:map.entrySet()){
			entry.setValue(AesUtil.decrypt(entry.getValue().toString(),trueKey));
		}
		return JSON.toJSONString(map);
	}
	/***
	* Description:加密
	* param:[body, key]
	* return:java.lang.String
	* Author:yu wenhao
	* date:2019/12/25
	*/
	public static Object encryptionString(String body,String key)throws Exception{
		String trueKey=RsaUtil.decryptHeader(key);
		Map<String,Object> map;
		JSONObject jsonObject=JSONObject.parseObject(body);
		map=JSON.toJavaObject(jsonObject,Map.class);
		if (map.get("data")==null){
			return map;
		}
		JSONObject jsonObject1=JSONObject.parseObject(map.get("data").toString());
		Map<String,Object> map1=JSON.toJavaObject(jsonObject1,Map.class);
		for (Map.Entry<String,Object> entry:map1.entrySet()){
			entry.setValue(AesUtil.encrypt(entry.getValue().toString(),trueKey));
		}
		map.put("data",map1);
		return map;
	}
}
