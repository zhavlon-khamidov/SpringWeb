package kg.edu.alatoo.spring_web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kg.edu.alatoo.spring_web.dao.UserDAO;
import kg.edu.alatoo.spring_web.exceptions.UsernameAlreadyExistsException;
import kg.edu.alatoo.spring_web.modules.User;
import kg.edu.alatoo.spring_web.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;



    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/register")
    public String registerPage(User user, Model model) {
        model.addAttribute(user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           @RequestParam String password2,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request, RedirectAttributesModelMap attributesModelMap) {
        model.addAttribute(user);

        // Check for password match
        if (!user.getPassword().equals(password2)) {
            model.addAttribute("msg", "Passwords didn't match");
            return "register";
        }

        // Check for user validation
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Creating user
        log.debug("Registering user {}",user);
        try {
            userService.createUser(user);
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("usernameExists", "Username '" + e.getUsername() + "' is not available");
            user.setUsername(null);
            return "register";
        }

        loginUser(user, request);

        return "redirect:/";
    }

    private static void loginUser(User user, HttpServletRequest request) {
        // Create security context and authenticate the user
        UserDAO userDAO = new UserDAO(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDAO,null);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);

        //Create session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
    }
}
