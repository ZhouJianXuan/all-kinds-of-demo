package work.koreyoshi.base.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;
import java.util.Map;

/**
 * @author ZhouJX
 */
public abstract class BaseService<M extends Model<M>> {

    /**
     * 必须实现
     * @return 获取对应的mapper
     */
    public abstract Model<M> getModel();

    public M findById(Object id) {
        return getModel().findById(id);
    }

    public boolean save(M model) {
        return getModel()._setAttrs(model).save();
    }

    public boolean save(Map<String, Object> attrs) {
        return getModel()._setAttrs(attrs).save();
    }

    public List<M> findAll() {
        return getModel().findAll();
    }

    public boolean update(M model) {
        return getModel()._setAttrs(model).update();
    }

    public boolean update(Map<String, Object> attrs) {
        return getModel()._setAttrs(attrs).update();
    }

    public boolean deleteById(Object id) {
        return getModel().deleteById(id);
    }

    public boolean deleteByIds(Object... id) {
        return getModel().deleteByIds(id);
    }

    public boolean delete(M model) {
        return getModel()._setAttrs(model).delete();
    }

    public boolean delete(Map<String, Object> attrs) {
        return getModel()._setAttrs(attrs).delete();
    }
}
