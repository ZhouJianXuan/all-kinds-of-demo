package work.koreyoshi.project.service;

import com.jfinal.plugin.activerecord.Model;
import org.springframework.stereotype.Service;
import work.koreyoshi.base.service.BaseService;
import work.koreyoshi.project.common.model.SysAccount;

/**
 * @author zhoujx
 */
@Service
public class SysAccountService extends BaseService<SysAccount> {

    private final static SysAccount MODEL = new SysAccount();

    @Override
    public Model<SysAccount> getModel() {
        return MODEL;
    }
}
