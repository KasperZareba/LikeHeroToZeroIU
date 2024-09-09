package com.project.Co2emission.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin/home")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @GetMapping("logout")
    public String logout() {return "login";}
}
