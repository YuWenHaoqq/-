package priv.wenhao.base.aop;

import com.google.common.base.Strings;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.util.RsaUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Component
@Aspect
public class TeacherLoginCheckAspect {
	@Autowired
	private RedisCommands<String, String> thirdTemplate;

	@Pointcut("@annotation(priv.wenhao.base.aop.TeacherLoginCheckAop)")
	public void LoginChecked() {
	}

	@Before("LoginChecked()")
	public void doBefore(JoinPoint jp) throws Exception {
//		获得请求头
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		String stuId = request.getHeader("id");
		String token = request.getHeader("token");
//		获得头部
		if (Strings.isNullOrEmpty(stuId) || Strings.isNullOrEmpty(token)) {
			throw new BussinessException(4, "凭证错误");
		}
//		System.out.println(RsaUtil.decryptHeader(stuId));
//		System.out.println(RsaUtil.decryptHeader(token));

		String trueToken = thirdTemplate.get(RsaUtil.decryptHeader(stuId));
//		System.out.println("trueToken:"+trueToken);
		if (!RsaUtil.decryptHeader(token).equals(trueToken)) {
			throw new BussinessException(4, "凭证错误,请重新登录");
		}
	}
}
