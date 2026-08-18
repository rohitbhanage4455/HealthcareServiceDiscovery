package com.example.HealthcareServiceDiscovery.Repository;

import com.example.HealthcareServiceDiscovery.Entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderServiceRepository
        extends JpaRepository<ProviderService, Long> {


    List<ProviderService> findByServiceId(Long serviceId);
    List<ProviderService> findByServiceIdOrderByPriceAsc(Long serviceId);
}