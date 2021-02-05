package com.weaselguard.weaselguardstoragemodule.repositories;

import com.weaselguard.weaselguardstoragemodule.models.IncidentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentTypeRepository extends CrudRepository<IncidentType, Long> {
}
