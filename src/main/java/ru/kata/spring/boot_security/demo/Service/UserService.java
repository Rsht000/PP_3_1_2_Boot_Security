package ru.kata.spring.boot_security.demo.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.Models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> displayAllUsers();

    public User getUserById(Long id);

    public User findByUserName(String username);

    public void addNewUser(User user);

    public void deleteUser(Long id);
}
