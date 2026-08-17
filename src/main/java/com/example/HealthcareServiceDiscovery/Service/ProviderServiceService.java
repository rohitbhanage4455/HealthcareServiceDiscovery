package com.example.HealthcareServiceDiscovery.Service;

import com.example.HealthcareServiceDiscovery.DTO.ProviderServiceDTO;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareProvider;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareService;
import com.example.HealthcareServiceDiscovery.Entity.ProviderService;
import com.example.HealthcareServiceDiscovery.Exception.ResourceNotFoundException;
import com.example.HealthcareServiceDiscovery.Repository.HealthcareProviderRepository;
import com.example.HealthcareServiceDiscovery.Repository.HealthcareServiceRepository;
import com.example.HealthcareServiceDiscovery.Repository.ProviderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceService {

    private final ProviderServiceRepository providerServiceRepository;
    private final HealthcareProviderRepository healthcareProviderRepository;
    private final HealthcareServiceRepository healthcareServiceRepository;

    public ProviderServiceService(
            ProviderServiceRepository providerServiceRepository,
            HealthcareProviderRepository healthcareProviderRepository,
            HealthcareServiceRepository healthcareServiceRepository) {

        this.providerServiceRepository = providerServiceRepository;
        this.healthcareProviderRepository = healthcareProviderRepository;
        this.healthcareServiceRepository = healthcareServiceRepository;
    }

    public ProviderServiceDTO addProviderService(
            ProviderServiceDTO providerServiceDTO) {

        HealthcareProvider provider =
                healthcareProviderRepository.findById(
                                providerServiceDTO.getProviderId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found with id: "
                                                + providerServiceDTO.getProviderId()
                                ));

        HealthcareService service =
                healthcareServiceRepository.findById(
                                providerServiceDTO.getServiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Healthcare service not found with id: "
                                                + providerServiceDTO.getServiceId()
                                ));

        ProviderService providerService = new ProviderService();

        providerService.setProvider(provider);
        providerService.setService(service);
        providerService.setPrice(providerServiceDTO.getPrice());

        ProviderService savedProviderService =
                providerServiceRepository.save(providerService);

        return convertToDTO(savedProviderService);
    }

    public List<ProviderServiceDTO> getAllProviderServices() {

        return providerServiceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProviderServiceDTO getProviderServiceById(Long id) {

        ProviderService providerService =
                providerServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider service not found with id: " + id
                                ));

        return convertToDTO(providerService);
    }

    public ProviderServiceDTO updateProviderService(
            Long id,
            ProviderServiceDTO updatedDTO) {

        ProviderService existingProviderService =
                providerServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider service not found with id: " + id
                                ));

        HealthcareProvider provider =
                healthcareProviderRepository.findById(
                                updatedDTO.getProviderId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found with id: "
                                                + updatedDTO.getProviderId()
                                ));

        HealthcareService service =
                healthcareServiceRepository.findById(
                                updatedDTO.getServiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Healthcare service not found with id: "
                                                + updatedDTO.getServiceId()
                                ));

        existingProviderService.setProvider(provider);
        existingProviderService.setService(service);
        existingProviderService.setPrice(updatedDTO.getPrice());

        ProviderService updatedProviderService =
                providerServiceRepository.save(existingProviderService);

        return convertToDTO(updatedProviderService);
    }

    public void deleteProviderService(Long id) {

        ProviderService existingProviderService =
                providerServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider service not found with id: " + id
                                ));

        providerServiceRepository.delete(existingProviderService);
    }

    private ProviderServiceDTO convertToDTO(
            ProviderService providerService) {

        ProviderServiceDTO dto = new ProviderServiceDTO();

        dto.setProviderId(
                providerService.getProvider().getId());

        dto.setServiceId(
                providerService.getService().getId());

        dto.setPrice(providerService.getPrice());

        return dto;
    }
}