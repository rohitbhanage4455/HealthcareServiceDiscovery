package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderServiceRepository
        extends JpaRepository<ProviderService, Long> {

}