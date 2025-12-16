package com.example.volunteersystem.controllers;

import com.example.volunteersystem.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public String events(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events/events";
    }

    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        model.addAttribute(
                "event",
                eventService.getEventById(id)
                        .orElseThrow(() -> new RuntimeException("Событие не найдено"))
        );
        return "events/event-details";
    }
}
