package ru.inbox.savinov_vu.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LoginController {

  @ResponseBody
  @GetMapping("/page/login")
  public String login() {
    return "LOGIN_PATH";
  }


  @ResponseBody
  @GetMapping("/page/sign-up")
  public String signUp() {
    return "SIGN_UP_PATH";
  }


}
