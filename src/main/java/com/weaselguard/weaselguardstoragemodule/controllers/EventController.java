package com.weaselguard.weaselguardstoragemodule.controllers;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public Iterable<Event> getEvents(@RequestParam Optional<Integer> limit) {
        return this.eventService.getLastEvents(limit.orElse(5));
    }


    @PostMapping("/event")
    public Event createEvent(@RequestBody Event event) {
        this.eventService.createEvent(event);
        return event;
    }

    @DeleteMapping("/event/{id}")
    public void updateEvent(@PathVariable Long id) {
        this.eventService.deleteEvent(id);
    }
}
