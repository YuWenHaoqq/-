package priv.wenhao.base.advice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import priv.wenhao.base.advice.inner.MyHttpInputMessage;
import priv.wenhao.base.exception.BussinessException;

import javax.crypto.BadPaddingException;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Description: 全局post请求参数处理
 * Author: yuWenHao
 * Date: 2019/12/25
 */
@Slf4j
@ControllerAdvice
public class GlobalDecodeRequestBodyAdvice implements RequestBodyAdvice {
	@Override
	public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@SneakyThrows
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
		boolean encode=false;
		if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)){
			SecurityParameter securityParameter=methodParameter.getMethodAnnotation(SecurityParameter.class);
			encode=securityParameter.inDecode();
		}
		if (encode){
			log.info("对方方法method:[" + methodParameter.getMethod().getName() + "]返回数集进行解密");
			try {
				return new MyHttpInputMessage(httpInputMessage);
			}catch (BussinessException e){
				throw new BussinessException(e.getCode(),e.getMessage());
			}
			catch (BadPaddingException e){
				throw new BussinessException(5,"解密错误");
			}
			catch (Exception e) {
				e.printStackTrace();
				log.info("对方方法method:[" + methodParameter.getMethod().getName() + "返回的数据进行解密失败");
				return httpInputMessage;
			}
		}else{
			return httpInputMessage;
		}
	}

	@Override
	public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return o;
	}

	@Override
	public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
		return o;
	}
}
