package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IFindIncidentsByOperatorUseCase;
import seminario.practica.sgi.incident.domain.Incident;

import java.util.List;

@RequiredArgsConstructor
public class FindIncidentByOperatorUseCaseService implements IFindIncidentsByOperatorUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public List<Incident> findByOperator(Long operatorId) {
        return incidentRepository.findByOperator(operatorId);
    }

}
