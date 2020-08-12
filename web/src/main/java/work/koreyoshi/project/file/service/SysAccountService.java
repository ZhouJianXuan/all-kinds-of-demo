package work.koreyoshi.project.file.service;

import com.jfinal.plugin.activerecord.Model;
import org.springframework.stereotype.Service;
import work.koreyoshi.base.service.BaseService;
import work.koreyoshi.project.common.model.Account;

/**
 * @author zhoujx
 */
@Service
public class SysAccountService extends BaseService<Account> {

    private final static Account MODEL = new Account();

    @Override
    public Model<Account> getModel() {
        return MODEL;
    }
}
