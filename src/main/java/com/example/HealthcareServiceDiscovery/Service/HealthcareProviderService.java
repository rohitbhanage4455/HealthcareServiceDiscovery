package com.example.HealthcareServiceDiscovery.Service;

import com.example.HealthcareServiceDiscovery.DTO.HealthcareProviderDTO;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareProvider;
import com.example.HealthcareServiceDiscovery.Exception.ResourceNotFoundException;
import com.example.HealthcareServiceDiscovery.Repository.HealthcareProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthcareProviderService {

    private final HealthcareProviderRepository healthcareProviderRepository;

    public HealthcareProviderService(
            HealthcareProviderRepository healthcareProviderRepository) {

        this.healthcareProviderRepository = healthcareProviderRepository;
    }

    public HealthcareProviderDTO addProvider(
            HealthcareProviderDTO providerDTO) {

        HealthcareProvider provider = new HealthcareProvider();

        provider.setName(providerDTO.getName());
        provider.setType(providerDTO.getType());
        provider.setAddress(providerDTO.getAddress());
        provider.setCity(providerDTO.getCity());
        provider.setState(providerDTO.getState());
        provider.setPincode(providerDTO.getPincode());
        provider.setPhone(providerDTO.getPhone());

        HealthcareProvider savedProvider =
                healthcareProviderRepository.save(provider);

        return convertToDTO(savedProvider);
    }

    public List<HealthcareProviderDTO> getAllProviders() {

        return healthcareProviderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public HealthcareProviderDTO getProviderById(Long id) {

        HealthcareProvider provider =
                healthcareProviderRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found with id: " + id
                                ));

        return convertToDTO(provider);
    }

    public HealthcareProviderDTO updateProvider(
            Long id,
            HealthcareProviderDTO updatedProviderDTO) {

        HealthcareProvider existingProvider =
                healthcareProviderRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found with id: " + id
                                ));

        existingProvider.setName(updatedProviderDTO.getName());
        existingProvider.setType(updatedProviderDTO.getType());
        existingProvider.setAddress(updatedProviderDTO.getAddress());
        existingProvider.setCity(updatedProviderDTO.getCity());
        existingProvider.setState(updatedProviderDTO.getState());
        existingProvider.setPincode(updatedProviderDTO.getPincode());
        existingProvider.setPhone(updatedProviderDTO.getPhone());

        HealthcareProvider updatedProvider =
                healthcareProviderRepository.save(existingProvider);

        return convertToDTO(updatedProvider);
    }

    public void deleteProvider(Long id) {

        HealthcareProvider existingProvider =
                healthcareProviderRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found with id: " + id
                                ));

        healthcareProviderRepository.delete(existingProvider);
    }

    private HealthcareProviderDTO convertToDTO(
            HealthcareProvider provider) {

        HealthcareProviderDTO dto = new HealthcareProviderDTO();

        dto.setName(provider.getName());
        dto.setType(provider.getType());
        dto.setAddress(provider.getAddress());
        dto.setCity(provider.getCity());
        dto.setState(provider.getState());
        dto.setPincode(provider.getPincode());
        dto.setPhone(provider.getPhone());

        return dto;
    }
    public List<HealthcareProviderDTO> getProvidersByCity(String city) {

        return healthcareProviderRepository
                .findByCityIgnoreCase(city)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<HealthcareProviderDTO> getProvidersByType(String type) {

        return healthcareProviderRepository
                .findByTypeIgnoreCase(type)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}