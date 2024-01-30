package ru.kata.spring.boot_security.demo.Controllers;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String userForm(Model model, Principal principal) {
        model.addAttribute("login",userService.findByUserName(principal.getName()));
        return "/user";
    }
}
