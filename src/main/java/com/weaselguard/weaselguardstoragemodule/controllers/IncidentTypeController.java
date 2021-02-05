package com.weaselguard.weaselguardstoragemodule.controllers;

import com.weaselguard.weaselguardstoragemodule.models.IncidentType;
import com.weaselguard.weaselguardstoragemodule.services.IncidentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncidentTypeController {

    private IncidentTypeService incidentTypeService;

    @Autowired
    public void setIncidentTypeService(IncidentTypeService incidentTypeService) {
        this.incidentTypeService = incidentTypeService;
    }

    @GetMapping("/incident_type/get")
    public Iterable<IncidentType> getIncidentTypes() {
        return this.incidentTypeService.getAllIncidentTypes();
    }

    @PostMapping("/incident_type/create")
    public IncidentType createIncidentType(IncidentType incidentType) {
        this.incidentTypeService.createIncidentType(incidentType);
        return incidentType;
    }

    @DeleteMapping("/incident_type/delete/{id}")
    public void deleteIncidentType(@PathVariable Long id) {
        this.incidentTypeService.deleteIncidentType(id);
    }
}
