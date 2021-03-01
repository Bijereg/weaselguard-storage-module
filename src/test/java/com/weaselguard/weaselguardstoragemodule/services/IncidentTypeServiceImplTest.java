package com.weaselguard.weaselguardstoragemodule.services;

import com.weaselguard.weaselguardstoragemodule.models.Incident;
import com.weaselguard.weaselguardstoragemodule.models.IncidentType;
import com.weaselguard.weaselguardstoragemodule.repositories.IncidentTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncidentTypeServiceImplTest {

    @TestConfiguration
    static class IncidentTypeServiceImplTestContextConfiguration {
        @Bean
        public IncidentTypeService incidentTypeService() {
            return new IncidentTypeServiceImpl();
        }
    }

    @MockBean
    private IncidentTypeRepository incidentTypeRepository;

    @Autowired
    private IncidentTypeService incidentTypeService;

    @Test
    void createIncidentType() {
        assertDoesNotThrow(() -> incidentTypeService.createIncidentType(new IncidentType()));
    }

    @Test
    void getAllIncidentTypes() {
        assertEquals(0,
                StreamSupport.stream(incidentTypeService.getAllIncidentTypes().spliterator(), false)
                        .count());

        Mockito.when(incidentTypeRepository.findAll()).thenReturn(Collections.nCopies(5, new IncidentType()));
        assertEquals(5,
                StreamSupport.stream(incidentTypeService.getAllIncidentTypes().spliterator(), false)
                        .count());
    }

    @Test
    void deleteIncidentType() {
        assertDoesNotThrow(() -> incidentTypeService.deleteIncidentType(-1L));
        assertDoesNotThrow(() -> incidentTypeService.deleteIncidentType(0L));
        assertDoesNotThrow(() -> incidentTypeService.deleteIncidentType(1L));
    }
}