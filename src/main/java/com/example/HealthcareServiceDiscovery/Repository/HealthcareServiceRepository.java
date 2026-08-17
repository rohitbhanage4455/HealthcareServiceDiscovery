package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.HealthcareService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthcareServiceRepository
        extends JpaRepository<HealthcareService, Long> {
    List<HealthcareService> findByNameContainingIgnoreCase(String name);
}