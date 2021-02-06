package com.weaselguard.weaselguardstoragemodule.controllers;

import com.weaselguard.weaselguardstoragemodule.models.Incident;
import com.weaselguard.weaselguardstoragemodule.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class IncidentController {

    private IncidentService incidentService;

    @Autowired
    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incident/get")
    public Iterable<Incident> getIncidents(@RequestParam Optional<Integer> limit) {
        return this.incidentService.getLastIncidents(limit.orElse(5));
    }


    @PostMapping("/incident/create")
    public Incident createIncident(@RequestBody Incident incident) {
        this.incidentService.createIncident(incident);
        return incident;
    }

    @PostMapping("/incident/{id}/comment")
    public void addComment(@PathVariable Long id, @RequestBody String comment) {
        try {
            this.incidentService.addComment(id, comment);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/incident/delete/{id}")
    public void deleteIncident(@PathVariable Long id) {
       this.incidentService.deleteIncident(id);
    }
}
