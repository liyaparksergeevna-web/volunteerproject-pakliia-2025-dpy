package com.example.volunteersystem.controllers;

import com.example.volunteersystem.models.Event;
import com.example.volunteersystem.models.EventStatus;
import com.example.volunteersystem.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    // USER и ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public String events(Model model) {

        List<Event> events = new ArrayList<>();

        Event e1 = new Event();
        e1.setTitle("Субботник");
        e1.setMaxParticipants(25);
        e1.setStatus(EventStatus.OPEN);

        Event e2 = new Event();
        e2.setTitle("Помощь приюту");
        e2.setMaxParticipants(40);
        e2.setStatus(EventStatus.CLOSED);

        Event e3 = new Event();
        e3.setTitle("Эко-фестиваль");
        e3.setMaxParticipants(0);
        e3.setStatus(EventStatus.SOON);

        events.add(e1);
        events.add(e2);
        events.add(e3);

        model.addAttribute("events", events);

        return "events/events";
    }
}
