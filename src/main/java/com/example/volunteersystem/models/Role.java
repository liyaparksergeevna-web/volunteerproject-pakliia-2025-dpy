package com.example.volunteersystem.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // например: "ROLE_USER", "ROLE_ADMIN", "ROLE_COORDINATOR", "ROLE_SUPER_ADMIN"
    @Column(nullable = false, unique = true)
    private String name;
}
