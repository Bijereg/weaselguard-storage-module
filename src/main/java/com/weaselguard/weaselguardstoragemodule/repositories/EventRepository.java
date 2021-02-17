package com.weaselguard.weaselguardstoragemodule.repositories;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findBySourceHost(@Param("sourceHost") String sourceHost);
    List<Event> findBySeverity(@Param("severity") Event.SEVERITY priority);
    List<Event> findByFacility(@Param("facility") int facility);
    List<Event> findAllByOrderByRegistrationDatetimeDescIdDesc(Pageable pageable);
}
