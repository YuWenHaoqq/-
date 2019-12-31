package priv.wenhao.base.advice.inner;


import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.util.HttpInputMessageUtil;


import java.io.IOException;
import java.io.InputStream;

public class MyHttpInputMessage implements HttpInputMessage {
	private HttpHeaders headers;
	private InputStream body;

	public MyHttpInputMessage(HttpInputMessage httpInputMessage) throws Exception {
		this.headers = httpInputMessage.getHeaders();
		String key=headers.getFirst("aes");
		if (Strings.isNullOrEmpty(key)) {
			throw new BussinessException(5,"请求错误,可以重启浏览器再重试");
		}
		String content=IOUtils.toString(httpInputMessage.getBody(), "UTF-8");
		this.body = IOUtils.toInputStream(HttpInputMessageUtil.decryptString(content,key), "UTF-8");
	}

	@Override
	public InputStream getBody() throws IOException {
		return body;
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

//	public String esapString(String requestData) throws Exception {
/////		注释,功能不符
////		if (requestData != null && !requestData.equals("")) {
////			String s = "{\"requestData\":";
////			if (!requestData.startsWith(s)) {
////				throw new RuntimeException("参数[requestData]确实异常");
////			} else {
////				int closeLen = requestData.length() - 1;
////				int openLen = "{\"requestData\":".length();
////				String subString = StringUtils.substring(requestData, openLen, closeLen);
////				return subString;
////			}
////		}
//		String json = AesUtil.decrypt(requestData);
//		return json;
//	}
}
