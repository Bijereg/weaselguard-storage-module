package com.weaselguard.weaselguardstoragemodule.controllers;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@Transactional
public class EventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event/get")
    public Iterable<Event> getEvents(@RequestParam Optional<Integer> limit) {
        return this.eventService.getLastEvents(limit.orElse(5));
    }


    @PostMapping("/event/create")
    public Event createEvent(@RequestBody Event event) {
        this.eventService.createEvent(event);
        return event;
    }

    @PostMapping("/event/{id}/comment")
    public void addComment(@PathVariable Long id, @RequestBody String comment) {
        try {
            this.eventService.addComment(id, comment);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/event/delete/{id}")
    public void deleteEvent(@PathVariable Long id) {
        this.eventService.deleteEvent(id);
    }
}
