package ru.inbox.savinov_vu.core.route;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RouteController {

    @GetMapping({"main", "usefulLinks", "news", "contact", "about", "login", "signup"})
    public ResponseEntity showMainPage() {
        ClassPathResource classPathResource = new ClassPathResource("static/index.html");
        return ResponseEntity.ok(classPathResource);
    }


}
