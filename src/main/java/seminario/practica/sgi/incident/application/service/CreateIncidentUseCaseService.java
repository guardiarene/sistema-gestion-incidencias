package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.ICreateIncidentUseCase;
import seminario.practica.sgi.incident.domain.Incident;

@RequiredArgsConstructor
public class CreateIncidentUseCaseService implements ICreateIncidentUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public void add(Incident incident) {
        incidentRepository.add(incident);
    }

}
