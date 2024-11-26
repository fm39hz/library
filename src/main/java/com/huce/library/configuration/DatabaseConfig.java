package com.huce.library.configuration;


import com.huce.library.modules.authentication.CustomPasswordEncoder;
import com.huce.library.modules.users.Roles;
import com.huce.library.modules.users.User;
import com.huce.library.modules.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class DatabaseConfig {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findAll().isEmpty()) {
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(Roles.USER);
                roles.add(Roles.ADMIN);
                User user = new User();
                user.setUsername("admin");
                user.setPassword(CustomPasswordEncoder.getInstance().encodePassword("@dmin"));
                user.setRole(roles);
                userRepository.save(user);
            }

        };
    }


}