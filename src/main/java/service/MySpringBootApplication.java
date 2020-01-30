package service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.CLASS)
public @interface MySpringBootApplication {
    String[] color() default "red";
    String gender();
}
