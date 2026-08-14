package com.example.HealthcareServiceDiscovery.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "provider_services")
public class ProviderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private HealthcareProvider provider;

    @ManyToOne
    private HealthcareService service;

    private Double price;

    // getters and setters


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public HealthcareService getService() {
        return service;
    }

    public void setService(HealthcareService service) {
        this.service = service;
    }

    public HealthcareProvider getProvider() {
        return provider;
    }

    public void setProvider(HealthcareProvider provider) {
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}