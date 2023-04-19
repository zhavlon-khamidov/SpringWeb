package kg.edu.alatoo.spring_web.controllers;

import kg.edu.alatoo.spring_web.modules.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/register")
    public String registerPage() {
        //TODO: provide register form
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        //TODO: handle register form
        return "redirect:/login";
    }
}
