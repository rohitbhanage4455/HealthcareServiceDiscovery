package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.HealthcareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareProviderRepository
        extends JpaRepository<HealthcareProvider, Long> {

}