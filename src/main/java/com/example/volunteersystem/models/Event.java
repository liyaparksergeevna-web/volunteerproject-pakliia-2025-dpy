package com.example.volunteersystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;            // название мероприятия
    private String description;      // описание
    private String location;         // место проведения
    private LocalDateTime dateTime;  // дата и время

    private Integer maxParticipants; // лимит участников

    // связь с координатором (пользователь)
    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    // связь с организацией
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // заявки на участие
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Application> applications;
}
