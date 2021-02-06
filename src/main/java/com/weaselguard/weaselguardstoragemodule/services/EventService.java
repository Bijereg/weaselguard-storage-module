package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;

public interface EventService {

    void createEvent(Event event);
    void addComment(Long id, String comment);
    Iterable<Event> getLastEvents(int limit);
    void deleteEvent(Long id);
}
