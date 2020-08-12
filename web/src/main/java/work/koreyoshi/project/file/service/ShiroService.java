package work.koreyoshi.project.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.koreyoshi.project.common.model.Account;
import work.koreyoshi.project.common.model.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoujx
 */
@Service
public class ShiroService {

    @Autowired
    private SysTokenService tokenService;
    @Autowired
    private SysAccountService accountService;

    public Token findToken(String token) {
        return tokenService.getModel().dao().findFirst("select * from sys_token where token = " + token + " limit 1");
    }

    public Account findAccountById(String id) {
        return accountService.findById(id);
    }

    public Set<String> getUserPermissions(String userId) {
        Set<String> strings = new HashSet<>();
        return strings;
    }
}
