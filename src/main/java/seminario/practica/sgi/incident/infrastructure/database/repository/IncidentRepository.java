package seminario.practica.sgi.incident.infrastructure.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.infrastructure.database.entity.IncidentEntity;
import seminario.practica.sgi.incident.infrastructure.database.mapper.IncidentEntityMapper;
import seminario.practica.sgi.incident.infrastructure.database.repository.abstraction.IIncidentSpringRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IncidentRepository implements IIncidentRepository {

    private final IIncidentSpringRepository incidentSpringRepository;

    private final IncidentEntityMapper incidentEntityMapper;

    @Override
    public List<Incident> findIncidentsByUsername(String username) {
        return incidentEntityMapper.toDomain(incidentSpringRepository.findByUsername(username));
    }

    @Override
    public void add(Incident incident) {
        IncidentEntity incidentEntity = incidentEntityMapper.toEntity(incident);
        incidentSpringRepository.save(incidentEntity);
    }

    @Override
    public Optional<Incident> findIncidentById(Long incidentId) {
        return incidentSpringRepository.findIncidentByIncidentId(incidentId).map(incidentEntityMapper::toDomain);
    }

    @Override
    public List<Incident> findAllIncidents() {
        return incidentEntityMapper.toDomain(incidentSpringRepository.findAll());
    }

    @Override
    public void assignOperator(Incident incident) {
        IncidentEntity incidentEntity = incidentEntityMapper.toEntityAssign(incident);
        incidentSpringRepository.assignOperator(incidentEntity);
    }

    @Override
    public List<Incident> findByOperator(Long operatorId) {
        return incidentEntityMapper.toDomain(incidentSpringRepository.findByOperator_UserId(operatorId));
    }

    @Override
    public void update(Incident incident) {
        IncidentEntity incidentEntity = incidentEntityMapper.toEntityUpdate(incident);
        incidentSpringRepository.update(incidentEntity);
    }

}
