package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {
    @GetMapping( "/login")
    public String login() {
        return "login";
    }
}
