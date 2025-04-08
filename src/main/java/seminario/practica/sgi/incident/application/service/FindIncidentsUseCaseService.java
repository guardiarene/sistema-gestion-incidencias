package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IFindIncidentsUseCase;
import seminario.practica.sgi.incident.domain.Incident;

import java.util.List;

@RequiredArgsConstructor
public class FindIncidentsUseCaseService implements IFindIncidentsUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public List<Incident> findAllIncidents() {
        return incidentRepository.findAllIncidents();
    }

}
