package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event) {
        if (event.getRegistrationDatetime() == null) {
            event.setRegistrationDatetime(LocalDateTime.now());
        }
        this.eventRepository.save(event);
    }

    @Override
    public Iterable<Event> getLastEvents(int limit) {
        return this.eventRepository.findAllByOrderByRegistrationDatetimeDesc(PageRequest.of(0, limit));
    }

    @Override
    public void deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }
}
