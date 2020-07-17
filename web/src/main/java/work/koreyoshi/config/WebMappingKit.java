package work.koreyoshi.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import org.springframework.stereotype.Component;
import work.koreyoshi.base.service.IActiveRecordInit;
import work.koreyoshi.project.common.model.MqIdempotentConsumeMessage;
import work.koreyoshi.project.common.model.MqMandatoryMessage;

/**
 * @author zhoujx
 */
@Component
public class WebMappingKit implements IActiveRecordInit {
    @Override
    public void addMapping(ActiveRecordPlugin arp) {
        arp.addMapping("mq_idempotent_consume_message", "id", MqIdempotentConsumeMessage.class);
        arp.addMapping("mq_mandatory_message", "id", MqMandatoryMessage.class);
    }
}
