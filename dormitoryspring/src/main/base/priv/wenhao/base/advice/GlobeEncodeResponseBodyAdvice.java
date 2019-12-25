package priv.wenhao.base.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import priv.wenhao.base.util.HttpInputMessageUtil;

/**
 * Description: 返回数据加密
 * Author: yuWenHao
 * Date: 2019/12/25
 */
@Slf4j
@ControllerAdvice
public class GlobeEncodeResponseBodyAdvice implements ResponseBodyAdvice {
	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		boolean encode = false;
		if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
//			获取注解配置的包含和去除字段
			SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
//			出参是否需要加密
			encode = serializedField.outEncode();
		}
		if (encode) {
			log.info("对方法method:[" + methodParameter.getMethod().getName() + "]返回的数据加密");
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
				return HttpInputMessageUtil.encryptionString(result,serverHttpRequest.getHeaders().getFirst("aes"));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
			}

		}
		return o;
	}
}
