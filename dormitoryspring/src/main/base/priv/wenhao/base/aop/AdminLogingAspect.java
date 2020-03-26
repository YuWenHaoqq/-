package priv.wenhao.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AdminLogingAspect {
	@Pointcut("@annotation(priv.wenhao.base.aop.AdminLogingAop)")
	public void checckLogin(){}

	@Before("checckLogin()")
	public void doBeford(){

	}
}
