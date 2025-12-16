package com.example.volunteersystem.config;

import com.example.volunteersystem.models.Role;
import com.example.volunteersystem.models.User;
import com.example.volunteersystem.repositories.RoleRepository;
import com.example.volunteersystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {

        if (userRepository.findByUsername("admin").isPresent()) {
            System.out.println("⚠ ADMIN уже существует");
            return;
        }

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Роль ADMIN не найдена"));

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(admin);

        System.out.println("✔ ADMIN создан: admin / admin123");
    }
}
