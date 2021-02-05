package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.IncidentType;

public interface IncidentTypeService {

    void createIncidentType(IncidentType incidentType);
    Iterable<IncidentType> getAllIncidentTypes();
    void deleteIncidentType(Long id);
}
