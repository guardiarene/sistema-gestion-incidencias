package seminario.practica.sgi.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seminario.practica.sgi.builder.IncidentBuilder;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.CreateIncidentUseCaseService;
import seminario.practica.sgi.incident.domain.Incident;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateIncidentUseCaseServiceTest {

    @Mock
    private IIncidentRepository incidentRepository;

    @Mock
    private CreateIncidentUseCaseService createIncidentUseCaseService;

    @Mock
    private Incident incident;

    @BeforeEach
    void setUp() {
        createIncidentUseCaseService = new CreateIncidentUseCaseService(incidentRepository);
    }

    @Test
    void shouldCreateIncident() {
        incident = IncidentBuilder.random();
        createIncidentUseCaseService.add(incident);
        verify(incidentRepository, times(1)).add(incident);
    }

}
