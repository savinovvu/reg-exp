package ru.inbox.savinov_vu.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "security/login/index";
    }

}
