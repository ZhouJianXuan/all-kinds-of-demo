package work.koreyoshi.mail.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import work.koreyoshi.mail.annotation.SendMail;

import javax.validation.constraints.Null;

/**
 * @author zhoujx
 */
public class SendMailAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private final Advice advice;
    private final Pointcut pointcut;

    public SendMailAnnotationAdvisor(@Null SendMailInterceptor interceptor) {
        this.advice = interceptor;
        this.pointcut = buildPointcut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }
    
    private Pointcut buildPointcut() {
        return AnnotationMatchingPointcut.forMethodAnnotation(SendMail.class);
    }
}
