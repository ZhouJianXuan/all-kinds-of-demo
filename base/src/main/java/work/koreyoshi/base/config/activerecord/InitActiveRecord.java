package work.koreyoshi.base.config.activerecord;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import work.koreyoshi.base.service.IActiveRecordInit;
import work.koreyoshi.base.util.InterfaceDispatcher;

/**
 * @author zhoujx
 */
@Component
@Order(value = 1)
public class InitActiveRecord implements ApplicationRunner {

    @Value("${active.record.jdbcUrl}")
    private String url;

    @Value("${active.record.user}")
    private String username;

    @Value("${active.record.password}")
    private String password;

    @Override
    public void run(ApplicationArguments args){
        initActiveRecord();
    }

    public void initActiveRecord() {
        DruidPlugin dp = new DruidPlugin(url, username, password);
        ActiveRecordPlugin arp = new ActiveRecordPlugin("mysql", dp);
        InterfaceDispatcher.build().collect(IActiveRecordInit.class)
                .forEach(e -> e.addMapping(arp));
        dp.start();
        arp.start();
    }
}
