package work.koreyoshi.project.admin.controller.service;

import com.jfinal.plugin.activerecord.Model;
import org.springframework.stereotype.Service;
import work.koreyoshi.base.service.BaseService;
import work.koreyoshi.project.common.model.Token;

/**
 * @author zhoujx
 */
@Service
public class SysTokenService extends BaseService<Token> {
    private final static Token MODEL = new Token();

    @Override
    public Model<Token> getModel() {
        return MODEL;
    }
}
