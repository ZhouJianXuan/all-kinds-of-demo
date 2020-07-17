package work.koreyoshi.base.service;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * @author zhoujx
 */
public interface IActiveRecordInit {

    /**
     * 添加映射
     * @param arp ActiveRecordPlugin
     */
    void addMapping(ActiveRecordPlugin arp);

}
