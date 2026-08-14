package com.example.HealthcareServiceDiscovery.Service;

import com.example.HealthcareServiceDiscovery.DTO.ProviderServiceDTO;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareProvider;
import com.example.HealthcareServiceDiscovery.Entity.HealthcareService;
import com.example.HealthcareServiceDiscovery.Entity.ProviderService;
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
                        .orElse(null);

        HealthcareService service =
                healthcareServiceRepository.findById(
                                providerServiceDTO.getServiceId())
                        .orElse(null);

        if (provider == null || service == null) {
            return null;
        }

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
                        .orElse(null);

        if (providerService == null) {
            return null;
        }

        return convertToDTO(providerService);
    }

    public ProviderServiceDTO updateProviderService(
            Long id,
            ProviderServiceDTO updatedDTO) {

        ProviderService existingProviderService =
                providerServiceRepository.findById(id)
                        .orElse(null);

        if (existingProviderService == null) {
            return null;
        }

        HealthcareProvider provider =
                healthcareProviderRepository.findById(
                                updatedDTO.getProviderId())
                        .orElse(null);

        HealthcareService service =
                healthcareServiceRepository.findById(
                                updatedDTO.getServiceId())
                        .orElse(null);

        if (provider == null || service == null) {
            return null;
        }

        existingProviderService.setProvider(provider);
        existingProviderService.setService(service);
        existingProviderService.setPrice(updatedDTO.getPrice());

        ProviderService updatedProviderService =
                providerServiceRepository.save(existingProviderService);

        return convertToDTO(updatedProviderService);
    }

    public void deleteProviderService(Long id) {

        providerServiceRepository.deleteById(id);
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