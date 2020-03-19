package priv.wenhao.base.aop;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CheckTimeAop {
}
