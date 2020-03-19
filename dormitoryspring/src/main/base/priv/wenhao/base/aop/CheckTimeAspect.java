package priv.wenhao.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import priv.wenhao.base.exception.BussinessException;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class CheckTimeAspect {
	@Pointcut("@annotation(priv.wenhao.base.aop.CheckTimeAop)")
	public void CheckTimeAop() {
	}
	@Before("CheckTimeAop()")
	public void doBefore()throws Exception{
		Date date=new Date();
		if (date.getHours()!=22){
			throw new BussinessException(1,"不在签到时间以内,请在每晚10点准时签到");
		}

	}
}
