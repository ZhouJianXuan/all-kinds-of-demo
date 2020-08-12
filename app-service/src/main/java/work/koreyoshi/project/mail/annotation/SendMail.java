package work.koreyoshi.project.mail.annotation;

import java.lang.annotation.*;

/**
 * @author zhoujx
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface SendMail {

    String msg();

    String title();

    String tos();

    boolean isHtml() default false;
}
