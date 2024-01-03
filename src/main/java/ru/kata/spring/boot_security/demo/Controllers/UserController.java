package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Repository.RoleRepository;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping(value = "/admin", produces = "text/html; charset=UTF-8")
    public String carList(Model model) {
        List<User> users = userService.displayAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/addNewUser", produces = "text/html; charset=UTF-8")
    public String newCar(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", (List<Role>) roleRepository.findAll());
        return "/addNewUser";
    }

    @PostMapping(value = "/addNewUser", produces = "text/html; charset=UTF-8")
    public String create(User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user", produces = "text/html; charset=UTF-8")
    public String userUpdateForm(Principal principal, Model model) {
        User user1 = userService.findByUserName(principal.getName());
        model.addAttribute("user", user1);
        return "/user";
    }

    @GetMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", (List<Role>) roleRepository.findAll());
        return "/Update-User";
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

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:admin";

    }
}
