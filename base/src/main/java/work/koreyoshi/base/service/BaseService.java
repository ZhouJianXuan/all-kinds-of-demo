package work.koreyoshi.base.service;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * @author ZhouJX
 */
public abstract class BaseService<M extends Model> {

    /**
     * 必须实现
     * @return 获取对应的mapper
     */
    public abstract Model<M> getModel();

}
