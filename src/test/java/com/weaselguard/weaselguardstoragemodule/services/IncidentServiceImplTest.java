package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import com.weaselguard.weaselguardstoragemodule.models.Incident;
import com.weaselguard.weaselguardstoragemodule.repositories.IncidentRepository;
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
class IncidentServiceImplTest {

    @TestConfiguration
    static class IncidentServiceImplTestContextConfiguration {
        @Bean
        public IncidentService incidentService() {
            return new IncidentServiceImpl();
        }
    }

    @MockBean
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentService incidentService;

    @Test
    void createIncident() {
        Incident incident = new Incident();
        LocalDateTime currentDateTime = LocalDateTime.now();
        incidentService.createIncident(incident);
        LocalDateTime eventDateTime = incident.getRegistrationDatetime();
        assertTrue(currentDateTime.isBefore(eventDateTime) || currentDateTime.isEqual(eventDateTime));

        incident = new Incident();
        incident.setRegistrationDatetime(currentDateTime);
        incidentService.createIncident(incident);
        assertEquals(currentDateTime, incident.getRegistrationDatetime());
    }

    @Test
    void addComment() {
        Mockito.when(incidentRepository.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> incidentService.addComment(-1L, "Test comment"));

        Mockito.when(incidentRepository.findById(0L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> incidentService.addComment(0L, "Test comment"));

        Incident incident = new Incident();
        Mockito.when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
        incidentService.addComment(1L, "Test comment");
        assertEquals("Test comment", incident.getComment());
    }

    @Test
    void getLastIncidents() {
        assertEquals(0,
                StreamSupport.stream(incidentService.getLastIncidents(0).spliterator(), false)
                        .count());

        assertEquals(0,
                StreamSupport.stream(incidentService.getLastIncidents(-1).spliterator(), false)
                        .count());

        Mockito.when(
                incidentRepository.findAllByOrderByRegistrationDatetimeDesc(
                        PageRequest.of(0, 5))
        ).thenReturn(Collections.nCopies(5, new Incident()));
        assertEquals(5,
                StreamSupport.stream(incidentService.getLastIncidents(5).spliterator(), false)
                        .count());
    }

    @Test
    void deleteIncident() {
        assertDoesNotThrow(() -> incidentService.deleteIncident(-1L));
        assertDoesNotThrow(() -> incidentService.deleteIncident(0L));
        assertDoesNotThrow(() -> incidentService.deleteIncident(1L));
    }
}