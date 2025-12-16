package com.example.volunteersystem.repositories;

import com.example.volunteersystem.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
