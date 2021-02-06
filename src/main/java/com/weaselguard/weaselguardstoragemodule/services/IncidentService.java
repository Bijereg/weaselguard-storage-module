package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Incident;

public interface IncidentService {

    void createIncident(Incident incident);
    void addComment(Long id, String comment);
    Iterable<Incident> getLastIncidents(int limit);
    void deleteIncident(Long id);
}
