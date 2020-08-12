package work.koreyoshi.project.mail.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import work.koreyoshi.project.mail.aop.SendMailAnnotationAdvisor;
import work.koreyoshi.project.mail.aop.SendMailInterceptor;

//@Configuration
public class SendMailConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SendMailInterceptor mailInterceptor() {
        return new SendMailInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public SendMailAnnotationAdvisor mailAnnotationAdvisor(SendMailInterceptor sendMailInterceptor) {
        return new SendMailAnnotationAdvisor(sendMailInterceptor);
    }
}
