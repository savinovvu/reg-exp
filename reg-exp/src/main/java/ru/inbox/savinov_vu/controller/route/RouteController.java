package ru.inbox.savinov_vu.controller.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class RouteController {

    @GetMapping({"main", "usefulLinks", "news", "contact", "about", "login", "signup"})
    public RedirectView showMainPage(RedirectAttributes attributes) {
        return new RedirectView("/");
    }


}
