package com.example.HealthcareServiceDiscovery.Service;

import com.example.HealthcareServiceDiscovery.DTO.HealthcareServiceDTO;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareService;
import com.example.HealthcareServiceDiscovery.Exception.ResourceNotFoundException;
import com.example.HealthcareServiceDiscovery.Repository.HealthcareServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthcareServiceService {

    private final HealthcareServiceRepository healthcareServiceRepository;

    public HealthcareServiceService(
            HealthcareServiceRepository healthcareServiceRepository) {

        this.healthcareServiceRepository = healthcareServiceRepository;
    }

    public HealthcareServiceDTO addService(
            HealthcareServiceDTO serviceDTO) {

        HealthcareService service = new HealthcareService();

        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());

        HealthcareService savedService =
                healthcareServiceRepository.save(service);

        return convertToDTO(savedService);
    }

    public List<HealthcareServiceDTO> getAllServices() {

        return healthcareServiceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public HealthcareServiceDTO getServiceById(Long id) {

        HealthcareService service =
                healthcareServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Healthcare service not found with id: " + id
                                ));

        return convertToDTO(service);
    }

    public HealthcareServiceDTO updateService(
            Long id,
            HealthcareServiceDTO updatedServiceDTO) {

        HealthcareService existingService =
                healthcareServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Healthcare service not found with id: " + id
                                ));

        existingService.setName(updatedServiceDTO.getName());
        existingService.setDescription(
                updatedServiceDTO.getDescription());

        HealthcareService updatedService =
                healthcareServiceRepository.save(existingService);

        return convertToDTO(updatedService);
    }

    public void deleteService(Long id) {

        HealthcareService existingService =
                healthcareServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Healthcare service not found with id: " + id
                                ));

        healthcareServiceRepository.delete(existingService);
    }

    private HealthcareServiceDTO convertToDTO(
            HealthcareService service) {

        HealthcareServiceDTO dto = new HealthcareServiceDTO();

        dto.setName(service.getName());
        dto.setDescription(service.getDescription());

        return dto;
    }
}