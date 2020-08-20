package work.koreyoshi.project.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import work.koreyoshi.base.result.RestResult;

/**
 * @author zhoujx
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public RestResult login() {
        return RestResult.ok();
    }

}
