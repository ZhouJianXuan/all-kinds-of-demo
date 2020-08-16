package work.koreyoshi.config.oauth2;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.koreyoshi.project.common.model.Token;
import work.koreyoshi.project.file.service.ShiroService;
import work.koreyoshi.project.common.model.Account;

import java.util.Set;

/**
 * @author zhoujx
 * 自定义Realm
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Account account = (Account) principal.getPrimaryPrincipal();
        Set<String> permissions = shiroService.getUserPermissions(account.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        Token first = shiroService.findToken(token);
        if (first.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token已失效，请重新登录");
        }
        Account account = shiroService.findAccountById(first.getUserId());
        return new SimpleAuthenticationInfo(account, token, account.getName());
    }
}
