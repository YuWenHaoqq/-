package priv.wenhao.base.util;

import java.util.UUID;

public class UUIDUtil {
	/***
	* Description:UUIDUtil
	* param:[]
	* return:java.lang.String
	* Author:yu wenhao
	* date:2019/12/17
	*/
	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-","").toLowerCase();
	}

}
