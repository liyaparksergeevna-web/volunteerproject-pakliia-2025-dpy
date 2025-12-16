package com.example.volunteersystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.volunteersystem.models.Event;
import com.example.volunteersystem.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
