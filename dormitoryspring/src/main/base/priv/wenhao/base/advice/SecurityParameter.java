package priv.wenhao.base.advice;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Description: 请求数据解密
 * Author: yuWenHao
 * Date: 2019/12/23
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecurityParameter {
	/**
	 * 入参是否解密,默认不解密
	 *
	 * @return
	 */
	boolean inDecode() default false;

	/**
	 * 出参是否加密,默认不加密
	 *
	 * @return
	 */
	boolean outEncode() default false;
}
