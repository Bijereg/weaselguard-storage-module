package com.weaselguard.weaselguardstoragemodule.repositories;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.models.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {
    List<Incident> findAllByOrderByRegistrationDatetimeDesc(Pageable pageable);
    List<Incident> findByEventsContains(Event event);
    List<Incident> findByAuthor(@Param("author") String author);
}
