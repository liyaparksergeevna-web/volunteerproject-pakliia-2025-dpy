package com.example.volunteersystem.repositories;

import com.example.volunteersystem.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
