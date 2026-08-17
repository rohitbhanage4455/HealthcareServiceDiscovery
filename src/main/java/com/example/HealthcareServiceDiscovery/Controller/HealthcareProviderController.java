package com.example.HealthcareServiceDiscovery.Controller;

import com.example.HealthcareServiceDiscovery.DTO.HealthcareProviderDTO;
import com.example.HealthcareServiceDiscovery.Service.HealthcareProviderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class HealthcareProviderController {

    private final HealthcareProviderService healthcareProviderService;

    public HealthcareProviderController(
            HealthcareProviderService healthcareProviderService) {

        this.healthcareProviderService = healthcareProviderService;
    }

    @PostMapping
    public HealthcareProviderDTO addProvider(
            @Valid @RequestBody HealthcareProviderDTO providerDTO) {

        return healthcareProviderService.addProvider(providerDTO);
    }

    @GetMapping
    public List<HealthcareProviderDTO> getAllProviders() {

        return healthcareProviderService.getAllProviders();
    }

    @GetMapping("/search")
    public List<HealthcareProviderDTO> getProvidersByCity(
            @RequestParam String city) {

        return healthcareProviderService.getProvidersByCity(city);
    }

    @GetMapping("/search/type")
    public List<HealthcareProviderDTO> getProvidersByType(
            @RequestParam String type) {

        return healthcareProviderService.getProvidersByType(type);
    }

    @GetMapping("/{id}")
    public HealthcareProviderDTO getProviderById(
            @PathVariable Long id) {

        return healthcareProviderService.getProviderById(id);
    }

    @PutMapping("/{id}")
    public HealthcareProviderDTO updateProvider(
            @PathVariable Long id,
            @Valid @RequestBody HealthcareProviderDTO providerDTO) {

        return healthcareProviderService.updateProvider(id, providerDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProvider(@PathVariable Long id) {

        healthcareProviderService.deleteProvider(id);

        return "Provider deleted successfully";
    }
}