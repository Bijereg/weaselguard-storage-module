package com.weaselguard.weaselguardstoragemodule.controllers;

import com.weaselguard.weaselguardstoragemodule.models.Incident;
import com.weaselguard.weaselguardstoragemodule.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class IncidentController {

    private IncidentService incidentService;

    @Autowired
    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents")
    public Iterable<Incident> getIncidents(@RequestParam Optional<Integer> limit) {
        return this.incidentService.getLastIncidents(limit.orElse(5));
    }


    @PostMapping("/incident")
    public Incident createIncident(@RequestBody Incident incident) {
        this.incidentService.createIncident(incident);
        return incident;
    }

    @DeleteMapping("/incident/{id}")
    public void updateIncident(@PathVariable Long id) {
       this.incidentService.deleteIncident(id);
    }
}
