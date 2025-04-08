package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IUpdateIncidentUseCase;
import seminario.practica.sgi.incident.domain.Incident;

@RequiredArgsConstructor
public class UpdateIncidentUseCaseService implements IUpdateIncidentUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public void updateIncident(Incident incident) {
        incidentRepository.update(incident);
    }

}
