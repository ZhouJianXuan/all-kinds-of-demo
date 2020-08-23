package work.koreyoshi.base.aop;

import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import work.koreyoshi.base.exception.SendException;
import work.koreyoshi.base.MailInfo;
import work.koreyoshi.base.annotation.SendMail;

/**
 * @author zhoujx
 */
@Slf4j
public class SendMailInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        SendMail sendMail = invocation.getMethod().getAnnotation(SendMail.class);
        MailInfo info = new MailInfo();
        try {
            MailUtil.sendText(info.getList(), info.getSubject(), info.getContent());
            return invocation.proceed();
        } catch (Exception e) {
            log.info("EMail发送失败，收件人列表：{}，标题和内容：{}:{}", info.getList(), info.getSubject(), info.getContent());
            throw SendException.sendMailError();
        }
    }
}
