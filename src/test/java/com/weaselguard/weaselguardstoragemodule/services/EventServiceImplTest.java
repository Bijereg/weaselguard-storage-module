package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceImplTest {

    @TestConfiguration
    static class EventServiceImplTestContextConfiguration {
        @Bean
        public EventService eventService() {
            return new EventServiceImpl();
        }
    }

    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Test
    void createEvent() {
        Event event = new Event();
        LocalDateTime currentDateTime = LocalDateTime.now();
        eventService.createEvent(event);
        LocalDateTime eventDateTime = event.getRegistrationDatetime();
        assertTrue(currentDateTime.isBefore(eventDateTime) || currentDateTime.isEqual(eventDateTime));

        event = new Event();
        event.setRegistrationDatetime(currentDateTime);
        eventService.createEvent(event);
        assertEquals(currentDateTime, event.getRegistrationDatetime());
    }

    @Test
    void addComment() {
        Mockito.when(eventRepository.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> eventService.addComment(-1L, "Test comment"));

        Mockito.when(eventRepository.findById(0L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> eventService.addComment(0L, "Test comment"));

        Event event = new Event();
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        eventService.addComment(1L, "Test comment");
        assertEquals("Test comment", event.getComment());
    }

    @Test
    void getLastEvents() {
        assertEquals(0,
                StreamSupport.stream(eventService.getLastEvents(0).spliterator(), false)
                        .count());

        assertEquals(0,
                StreamSupport.stream(eventService.getLastEvents(-1).spliterator(), false)
                        .count());

        Mockito.when(
                eventRepository.findAllByOrderByRegistrationDatetimeDescIdDesc(
                        PageRequest.of(0, 5))
        ).thenReturn(Collections.nCopies(5, new Event()));
        assertEquals(5,
                StreamSupport.stream(eventService.getLastEvents(5).spliterator(), false)
                        .count());
    }

    @Test
    void deleteEvent() {
        assertDoesNotThrow(() -> eventService.deleteEvent(-1L));
        assertDoesNotThrow(() -> eventService.deleteEvent(0L));
        assertDoesNotThrow(() -> eventService.deleteEvent(1L));
    }
}