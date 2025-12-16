package com.example.volunteersystem.repositories;

import com.example.volunteersystem.models.Application;
import com.example.volunteersystem.models.Event;
import com.example.volunteersystem.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByEvent(Event event);

    List<Application> findByVolunteer(Volunteer volunteer);

    Optional<Application> findByEventAndVolunteer(Event event, Volunteer volunteer);
}