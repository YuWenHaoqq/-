package priv.wenhao.base.em;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * ExceptionEnum Tester.
 *
 * @author yu wenhao
 * @version 1.0
 * @since <pre>12/19/2019</pre>
 */

public class ExceptionEnumTest {

	@Test
	public void test() throws Exception {
		Class<?> clz = ExceptionEnum.class;
//		得到所有的枚举类型
		Object[] objects = clz.getEnumConstants();
		Method getCode=clz.getMethod("getCode");
		Method getMessage=clz.getMethod("getMessage");
		for (Object obj:objects){
			System.out.println("code="+getCode.invoke(obj)+";message="+getMessage.invoke(obj));
		}
	}
} 
