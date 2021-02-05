package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;

public interface EventService {

    void createEvent(Event event);
    Iterable<Event> getLastEvents(int limit);
    void deleteEvent(Long id);
}
