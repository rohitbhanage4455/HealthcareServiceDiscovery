package com.example.HealthcareServiceDiscovery.Controller;

import com.example.HealthcareServiceDiscovery.DTO.ProviderServiceDTO;
import com.example.HealthcareServiceDiscovery.Service.ProviderServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider-services")
public class ProviderServiceController {

    private final ProviderServiceService providerServiceService;

    public ProviderServiceController(
            ProviderServiceService providerServiceService) {

        this.providerServiceService = providerServiceService;
    }

    @PostMapping
    public ProviderServiceDTO addProviderService(
            @RequestBody ProviderServiceDTO providerServiceDTO) {

        return providerServiceService.addProviderService(providerServiceDTO);
    }

    @GetMapping
    public List<ProviderServiceDTO> getAllProviderServices() {

        return providerServiceService.getAllProviderServices();
    }

    @GetMapping("/{id}")
    public ProviderServiceDTO getProviderServiceById(
            @PathVariable Long id) {

        return providerServiceService.getProviderServiceById(id);
    }

    @PutMapping("/{id}")
    public ProviderServiceDTO updateProviderService(
            @PathVariable Long id,
            @RequestBody ProviderServiceDTO providerServiceDTO) {

        return providerServiceService.updateProviderService(
                id, providerServiceDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProviderService(@PathVariable Long id) {

        providerServiceService.deleteProviderService(id);

        return "Provider service deleted successfully";
    }
}