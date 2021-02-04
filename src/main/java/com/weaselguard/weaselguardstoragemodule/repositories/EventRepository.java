package com.weaselguard.weaselguardstoragemodule.repositories;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findBySourceAddress(@Param("sourceAddress") String sourceAddress);

    List<Event> findByPriority(@Param("priority") Event.PRIORITY priority);

    List<Event> findAllByOrderByRegistrationDatetimeDesc(Pageable pageable);
}
