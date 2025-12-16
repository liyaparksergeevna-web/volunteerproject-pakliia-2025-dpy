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

    // === ОСНОВНЫЕ ДАННЫЕ ===
    private String title;            // название мероприятия
    private String description;      // описание
    private String location;         // место проведения
    private LocalDateTime dateTime;  // дата и время

    private Integer maxParticipants; // лимит участников

    // === СТАТУС ===
    @Enumerated(EnumType.STRING)
    private EventStatus status;      // OPEN, CLOSED, SOON

    // === СВЯЗИ ===
    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Application> applications;

    // === ВЫЧИСЛЯЕМЫЕ ПОЛЯ (для HTML) ===

    @Transient
    public int getParticipantsCount() {
        return applications == null ? 0 : applications.size();
    }

    @Transient
    public String getName() {
        return title;
    }
}
