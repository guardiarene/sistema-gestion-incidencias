package seminario.practica.sgi.incident.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.usecase.IFindIncidentsByUsernameUseCase;
import seminario.practica.sgi.incident.domain.Incident;

import java.util.List;

@RequiredArgsConstructor
public class FindIncidentsByUsernameUseCaseService implements IFindIncidentsByUsernameUseCase {

    private final IIncidentRepository incidentRepository;

    @Override
    public List<Incident> findByUsername(String username) {
        return incidentRepository.findIncidentsByUsername(username);
    }

}
