package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.HealthcareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthcareProviderRepository
        extends JpaRepository<HealthcareProvider, Long> {

    List<HealthcareProvider> findByCityIgnoreCase(String city);
    List<HealthcareProvider> findByTypeIgnoreCase(String type);
}