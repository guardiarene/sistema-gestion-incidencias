package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.common.home.infrastructure.config.spring.exception.ErrorMessage;
import seminario.practica.sgi.incident.application.exception.IncidentDoesNotExist;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IFindIncidentByIdUseCase;
import seminario.practica.sgi.incident.domain.Incident;

import java.util.Optional;

@RequiredArgsConstructor
public class FindIncidentByIdUseCaseService implements IFindIncidentByIdUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public Incident findById(Long incidentId) {
        Optional<Incident> incident = incidentRepository.findIncidentById(incidentId);
        if (incident.isEmpty()) {
            throw new IncidentDoesNotExist(ErrorMessage.INCIDENT_DOES_NOT_EXISTS.getMessage());
        }
        return incident.get();
    }

}
