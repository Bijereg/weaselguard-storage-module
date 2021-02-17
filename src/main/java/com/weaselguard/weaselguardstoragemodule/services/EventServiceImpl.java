package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public void addComment(Long id, String comment) throws IllegalArgumentException {
        Optional<Event> event = this.eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("There is no event with id = " + id);
        }
        else {
            Event e = event.get();
            e.setComment(comment);
            this.eventRepository.save(e);
        }
    }

    @Override
    public Iterable<Event> getLastEvents(int limit) {
        return this.eventRepository.findAllByOrderByRegistrationDatetimeDescIdDesc(PageRequest.of(0, limit));
    }

    @Override
    public void deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }
}
