package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Repository.RoleRepository;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }
    @GetMapping()
    public String carList(Model model, Principal principal) {
        User userTest = userService.findByUserName(principal.getName());
        model.addAttribute("users", userService.displayAllUsers());
        model.addAttribute("allRoles", (List<Role>) roleRepository.findAll());
        model.addAttribute("login", userTest);
        return "admin";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", (List<Role>) roleRepository.findAll());
        return "Login";
    }

    @PostMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam("roles") Set<Role> role,
                             @RequestParam("id") User user) {

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
