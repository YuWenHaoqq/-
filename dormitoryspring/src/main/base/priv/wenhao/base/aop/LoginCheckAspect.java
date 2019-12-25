package priv.wenhao.base.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;

public class LoginCheckAspect {


	@Pointcut("@annotation(priv.wenhao.base.aop.LoginCheckAop)")
	public void LoginChecked(){}

//	@Bean
}
