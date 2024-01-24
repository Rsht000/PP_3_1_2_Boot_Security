package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {


    public final UserServiceImpl userServiceImpl;


    public RestAdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;

    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> usersList = userServiceImpl.displayAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userServiceImpl.addNewUser(user);
        return null;
    }

    @PutMapping(value = "/getUser/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userServiceImpl.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/getUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
