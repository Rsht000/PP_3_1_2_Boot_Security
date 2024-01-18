package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;

import java.security.Principal;
@RestController
@RequestMapping("/api")
public class UserRestController {
        private final UserServiceImpl userServiceImpl;

        public UserRestController(UserServiceImpl userServiceImpl) {
            this.userServiceImpl = userServiceImpl;
        }

        @GetMapping("/userPager")
        public ResponseEntity<User> userForm(Principal principal) {
            return new ResponseEntity<>(userServiceImpl.findByUserName(principal.getName()), HttpStatus.OK);
        }

}
