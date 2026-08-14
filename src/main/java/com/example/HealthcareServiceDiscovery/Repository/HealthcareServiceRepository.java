package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.HealthcareService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareServiceRepository
        extends JpaRepository<HealthcareService, Long> {

}