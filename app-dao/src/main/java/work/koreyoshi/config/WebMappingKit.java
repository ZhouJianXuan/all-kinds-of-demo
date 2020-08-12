package work.koreyoshi.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import org.springframework.stereotype.Component;
import work.koreyoshi.base.service.IActiveRecordInit;
import work.koreyoshi.project.common.model._MappingKit;

/**
 * @author zhoujx
 */
@Component
public class WebMappingKit implements IActiveRecordInit {
    @Override
    public void addMapping(ActiveRecordPlugin arp) {
        _MappingKit.mapping(arp);
    }
}
