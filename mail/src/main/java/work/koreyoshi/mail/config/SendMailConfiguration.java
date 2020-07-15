package work.koreyoshi.mail.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.koreyoshi.mail.aop.SendMailAnnotationAdvisor;
import work.koreyoshi.mail.aop.SendMailInterceptor;

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
