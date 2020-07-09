package com.koreyoshi.mail;

import cn.hutool.extra.mail.MailUtil;
import com.koreyoshi.mail.annotation.SendMail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Aspect
@Component
public class SendMailAspect {

    @Around(value = "@annotation(com.koreyoshi.mail.annotation.SendMail) && @annotation(sendMail)",
            argNames = "joinPoint, sendMail")
    public Object sendMail(ProceedingJoinPoint joinPoint, SendMail sendMail) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            throw new RuntimeException("EMail发送失败，参数为0");
        }
        String subject = (String) getParam(joinPoint, sendMail.title());
        String content = (String) getParam(joinPoint, sendMail.msg());
        String[] tos = (String[]) getParam(joinPoint, sendMail.tos());
        try {
            assert tos != null;
            MailUtil.sendText(Arrays.asList(tos), subject, content);
        } catch (Exception e) {
            throw new RuntimeException("EMail发送失败，收件人列表：" + Arrays.toString(tos) + "，标题和内容：" + subject + content);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    /**
     *
     * @param joinPoint 切点信息
     * @param oldParam 取参数名称
     * @return 符合规则的参数，目前符合（#0）或者（#0.title）
     */
    private Object getParam(ProceedingJoinPoint joinPoint, String oldParam) {
        if (oldParam.indexOf("#") == 0) {
            String[] strings = oldParam.replace("#", "").split("\\.");
            if (strings.length < 2) {
                return joinPoint.getArgs()[Integer.parseInt(strings[0])];
            }
            if (strings.length == 2) {
                Object param = joinPoint.getArgs()[Integer.parseInt(strings[0])];
                Class<?> o = param.getClass();
                try {
                    Field field = o.getDeclaredField(strings[1]);
                    // 暴力访问
                    field.setAccessible(true);
                    return field.get(param);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        throw new RuntimeException("参数不符合规则");
    }

}
