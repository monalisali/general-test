package service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//被@Log注解的方法，就会打印日志
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
