package work.koreyoshi.file.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import org.springframework.stereotype.Component;
import work.koreyoshi.base.service.IActiveRecordInit;
import work.koreyoshi.file.project.common.model.FileStorage;

/**
 * @author zhoujx
 */
@Component("fileWebMappingKit")
public class WebMappingKit implements IActiveRecordInit {
    @Override
    public void addMapping(ActiveRecordPlugin arp) {
        arp.addMapping("tb_file_storage", "id", FileStorage.class);
    }
}
