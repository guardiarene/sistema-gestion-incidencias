package seminario.practica.sgi.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seminario.practica.sgi.builder.IncidentBuilder;
import seminario.practica.sgi.incident.application.exception.IncidentDoesNotExist;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.AssignOperatorUseCaseService;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.domain.IncidentStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssignOperatorUseCaseServiceTest {

    @Mock
    private IIncidentRepository incidentRepository;

    @Mock
    private AssignOperatorUseCaseService assignOperatorUseCaseService;

    @Mock
    private Incident incident;

    @BeforeEach
    void setUp() {
        assignOperatorUseCaseService = new AssignOperatorUseCaseService(incidentRepository);
        incident = IncidentBuilder.assignedIncident();
    }

    @Test
    void shouldAssignOperatorSuccessfully() {
        when(incidentRepository.findIncidentById(incident.getIncidentId())).thenReturn(Optional.of(incident));
        assignOperatorUseCaseService.assignOperator(incident);
        assertEquals(IncidentStatus.ASSIGNED, incident.getStatus());
        verify(incidentRepository, times(1)).assignOperator(incident);
    }

    @Test
    void shouldThrowIncidentDoesNotExistWhenIncidentNotFound() {
        when(incidentRepository.findIncidentById(incident.getIncidentId())).thenReturn(Optional.empty());
        assertThrows(IncidentDoesNotExist.class, () -> assignOperatorUseCaseService.assignOperator(incident));
    }

}
