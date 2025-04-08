package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.common.home.infrastructure.config.spring.exception.ErrorMessage;
import seminario.practica.sgi.incident.application.exception.IncidentDoesNotExist;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IAssignOperatorUseCase;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.domain.IncidentStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class AssignOperatorUseCaseService implements IAssignOperatorUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public void assignOperator(Incident incident) {
        Optional<Incident> incidentFound = incidentRepository.findIncidentById(incident.getIncidentId());
        if (incidentFound.isEmpty()) {
            throw new IncidentDoesNotExist(ErrorMessage.INCIDENT_DOES_NOT_EXISTS.getMessage());
        }
        incident.setStatus(IncidentStatus.ASSIGNED);
        incidentRepository.assignOperator(incident);
    }

}
