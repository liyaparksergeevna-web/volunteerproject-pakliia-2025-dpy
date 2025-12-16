package com.example.volunteersystem.config;

import com.example.volunteersystem.models.Role;
import com.example.volunteersystem.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            String[] roles = {
                    "ROLE_USER",
                    "ROLE_ADMIN",
                    "ROLE_COORDINATOR",
                    "ROLE_SUPER_ADMIN"
            };

            for (String roleName : roles) {
                roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(Role.builder().name(roleName).build()));
            }

            System.out.println("✔ Роли добавлены в базу данных");
        };
    }
}
