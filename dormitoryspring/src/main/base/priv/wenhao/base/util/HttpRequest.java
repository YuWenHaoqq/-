package priv.wenhao.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * ClassName: HttpRequest
 * Description: 向第三方路径发起请求
 * Author: yuWenHao
 * Date: 2019/12/30
 */

@Slf4j
public class HttpRequest {
	/***
	 * ClassName:HttpRequest
	 * Description:发送get方式的请求
	 * param:[url, param]
	 * return:java.lang.String
	 * Author:yu wenhao
	 * date:2019/12/30
	 */
	public static String sendGet(String url, String param, String encoding) {
		StringBuilder result = new StringBuilder();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);

//			打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();

//			设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 建立实际的连接
			connection.connect();

			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();

			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			log.info(url + ":发送Get请求错误");
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/***
	 * ClassName:HttpRequest
	 * Description:发起post请求
	 * param:[url, param, encoding]
	 * return:java.lang.String
	 * Author:yu wenhao
	 * date:2019/12/30
	 */
	public static String sendPost(String url, String param, String encoding) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());

			// 发送请求参数
			out.print(param);

			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), encoding));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			log.info(url + ":发送post请求错误");
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
}

