package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Incident;
import com.weaselguard.weaselguardstoragemodule.repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncidentServiceImpl implements IncidentService {

    private IncidentRepository incidentRepository;

    @Autowired
    public void setIncidentRepository(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public void createIncident(Incident incident) {
        if (incident.getRegistrationDatetime() == null) {
            incident.setRegistrationDatetime(LocalDateTime.now());
        }
        this.incidentRepository.save(incident);
    }

    @Override
    public Iterable<Incident> getLastIncidents(int limit) {
        return this.incidentRepository.findAllByOrderByRegistrationDatetimeDesc(PageRequest.of(0, limit));
    }

    @Override
    public void deleteIncident(Long id) {
        this.incidentRepository.deleteById(id);
    }
}
