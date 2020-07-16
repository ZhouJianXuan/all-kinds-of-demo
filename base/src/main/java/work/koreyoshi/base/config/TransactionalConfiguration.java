package work.koreyoshi.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.koreyoshi.base.aop.transactional.TransactionalAnnotationAdvisor;
import work.koreyoshi.base.aop.transactional.TransactionalInterceptor;

/**
 * @author zhoujx
 */
@Configuration
public class TransactionalConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TransactionalInterceptor transactionalInterceptor() {
        return new TransactionalInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public TransactionalAnnotationAdvisor transactionalAnnotationAdvisor(TransactionalInterceptor transactionalInterceptor) {
        return new TransactionalAnnotationAdvisor(transactionalInterceptor);
    }

}
