package ru.kata.spring.boot_security.demo.Util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Repository.RoleRepository;
import ru.kata.spring.boot_security.demo.Repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@Component
public class DataBaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataBaseLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin", "admin", "admin");
        User user = new User("user", "user", "user");
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        this.roleRepository.save(adminRole);
        this.roleRepository.save(userRole);
        this.userRepository.save(admin);
        this.userRepository.save(user);

        admin.setRoles(new HashSet<>(List.of(adminRole, userRole)));
        user.setRoles(new HashSet<>(List.of(userRole)));

        this.userRepository.save(admin);
        this.userRepository.save(user);


    }
}
