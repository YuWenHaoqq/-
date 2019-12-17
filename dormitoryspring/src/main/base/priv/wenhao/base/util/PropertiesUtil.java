package priv.wenhao.base.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: PropertiesUtil
 * Author: yuWenHao
 * Date: 2019/12/17
 */

public class PropertiesUtil {
	/***
	 * Description:获取res.static下面的properties里面的配置文件
	 * param:[filePath, key]
	 * return:java.lang.String
	 * Author:yu wenhao
	 * date:2019/12/3
	 */
	public static String getPropertiesValue(String filePath, String key) {
		Resource resource = new ClassPathResource("config/" + filePath);
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = resource.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			properties.load(bf);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties.getProperty(key);
	}

	/***
	 * Description:获取整个res.static文件夹下面整个的文件内容
	 * param:[filePath]
	 * return:java.util.Map<java.lang.String,java.lang.String>
	 * Author:yu wenhao
	 * date:2019/12/3
	 */
	public static HashMap<String, String> getPropertiesMap(String filePath) {
		Resource resource = new ClassPathResource("config/" + filePath);
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = resource.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			properties.load(bf);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new HashMap<String, String>((Map) properties);
	}

/// 并不能对resources文件下进行写入
//	/***
//	 * Description:应该只在test方法中被调用
//	 * param:[map, filePath]
//	 * return:void
//	 * Author:yu wenhao
//	 * date:2019/12/17
//	 */
//	public static void writePropertiesMap(HashMap<String, String> map, String filePath, String des) {
//		Resource resource = new ClassPathResource("config/" + filePath);
//		Properties properties = new Properties();
//
//		try {
//			InputStream is = resource.getInputStream();
//			properties.load(is);
////			String file=resour;
////			System.out.println(resource.getFile().getPath());
//
//			File file = ResourceUtils.getFile("classpath:config/" + filePath);
//			System.out.println(file.getPath());
//			OutputStream os=new FileOutputStream(file.getPath());
//
////			System.out.println();
//			for (Map.Entry<String, String> enty : map.entrySet()) {
//				System.out.println(enty.getKey() + ":" + enty.getValue());
//				properties.setProperty(enty.getKey(), enty.getValue());
//			}
//			properties.store(os, des);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//	}
}
