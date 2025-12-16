package com.example.volunteersystem.controllers;

import com.example.volunteersystem.models.Role;
import com.example.volunteersystem.models.User;
import com.example.volunteersystem.repositories.RoleRepository;
import com.example.volunteersystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // -------- регистрация --------
    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {

        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Логин уже существует");
            return "registration";
        }

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(roleUser);

        userRepository.save(user);

        return "redirect:/login";
    }

    // -------- логин --------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // -------- профиль --------
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "profile";
    }
}
