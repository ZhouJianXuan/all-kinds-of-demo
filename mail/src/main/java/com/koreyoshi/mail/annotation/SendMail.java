package com.koreyoshi.mail.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface SendMail {

    String msg();

    String title();

    String tos();

    boolean isHtml() default false;
}
