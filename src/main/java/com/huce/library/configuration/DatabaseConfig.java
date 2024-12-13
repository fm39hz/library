package com.huce.library.configuration;

import com.huce.library.module.user.Roles;
import com.huce.library.module.user.User;
import com.huce.library.module.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseConfig {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DatabaseConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("@dmin"));
                admin.setRole(Roles.ADMIN);
                userRepository.save(admin);
            }
        };
    }
}
