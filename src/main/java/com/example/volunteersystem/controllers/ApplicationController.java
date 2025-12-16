package com.example.volunteersystem.controllers;

import com.example.volunteersystem.models.Application;
import com.example.volunteersystem.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    // ----- список всех заявок -----
    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public String listApplications(Model model) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "applications";
    }

    // ----- страница создания -----
    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("application", new Application());
        return "application_create";
    }

    // ----- сохранение заявки -----
    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/create")
    public String createApplication(@ModelAttribute Application application) {
        applicationRepository.save(application);
        return "redirect:/applications";
    }

    // ----- просмотр одной заявки -----
    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        model.addAttribute("application", app);
        return "application_view";
    }

    // ----- удаление заявки -----
    // ТОЛЬКО ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
        return "redirect:/applications";
    }
}
