package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.IncidentType;
import com.weaselguard.weaselguardstoragemodule.repositories.IncidentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentTypeServiceImpl implements IncidentTypeService {

    private IncidentTypeRepository incidentTypeRepository;

    @Autowired
    public void setIncidentTypeRepository(IncidentTypeRepository incidentTypeRepository) {
        this.incidentTypeRepository = incidentTypeRepository;
    }

    @Override
    public void createIncidentType(IncidentType incidentType) {
        this.incidentTypeRepository.save(incidentType);
    }

    @Override
    public Iterable<IncidentType> getAllIncidentTypes() {
        return this.incidentTypeRepository.findAll();
    }

    @Override
    public void deleteIncidentType(Long id) {
        this.incidentTypeRepository.deleteById(id);
    }
}
