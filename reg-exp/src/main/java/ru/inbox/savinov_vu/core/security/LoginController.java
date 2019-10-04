package ru.inbox.savinov_vu.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.inbox.savinov_vu.common.constant.TemplatePaths.LOGIN_PATH;



@Controller
public class LoginController {

    @GetMapping("/page/login")
    public String login() {
        return LOGIN_PATH;
    }

}
