//package priv.wenhao.base.advice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//import priv.wenhao.base.advice.inner.MyHttpInputMessage;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
///**
// * Description: 请求数据解密
// * Author: yuWenHao
// * Date: 2019/12/23
// */
//
//@Slf4j
//@ControllerAdvice
//public class DecodeRequestBodyAdvice implements RequestBodyAdvice {
//
//	@Override
//	public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return true;
//	}
//
//	@Override
//	public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
//		try {
//			boolean encode = false;
//			if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
////				获取注解配置的包含和去除字段
//				SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
////			入参是否解密
//				encode = serializedField.inDecode();
//			}
//			if (encode) {
//				log.info("对方方法method:[" + methodParameter.getMethod().getName() + "]返回数集进行解密");
//				return new MyHttpInputMessage(httpInputMessage);
//			} else {
//				return httpInputMessage;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("对方方法method:[" + methodParameter.getMethod().getName() + "返回的数据进行解密");
//			return httpInputMessage;
//		}
//	}
//
//	@Override
//	public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return o;
//	}
//
//	@Override
//	public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return o;
//	}
//
//}
