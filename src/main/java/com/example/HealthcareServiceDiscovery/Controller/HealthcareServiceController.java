package com.example.HealthcareServiceDiscovery.Controller;

import com.example.HealthcareServiceDiscovery.DTO.HealthcareServiceDTO;
import com.example.HealthcareServiceDiscovery.Service.HealthcareServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class HealthcareServiceController {

    private final HealthcareServiceService healthcareServiceService;

    public HealthcareServiceController(
            HealthcareServiceService healthcareServiceService) {

        this.healthcareServiceService = healthcareServiceService;
    }

    @PostMapping
    public HealthcareServiceDTO addService(
            @RequestBody HealthcareServiceDTO serviceDTO) {

        return healthcareServiceService.addService(serviceDTO);
    }

    @GetMapping
    public List<HealthcareServiceDTO> getAllServices() {

        return healthcareServiceService.getAllServices();
    }

    @GetMapping("/search")
    public List<HealthcareServiceDTO> searchServices(
            @RequestParam String name) {

        return healthcareServiceService.searchServices(name);
    }

    @GetMapping("/{id}")
    public HealthcareServiceDTO getServiceById(
            @PathVariable Long id) {

        return healthcareServiceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    public HealthcareServiceDTO updateService(
            @PathVariable Long id,
            @RequestBody HealthcareServiceDTO serviceDTO) {

        return healthcareServiceService.updateService(id, serviceDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {

        healthcareServiceService.deleteService(id);

        return "Healthcare service deleted successfully";
    }
}