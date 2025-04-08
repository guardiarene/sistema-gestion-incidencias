package seminario.practica.sgi.incident.application.repository;

import seminario.practica.sgi.incident.domain.Incident;

import java.util.List;
import java.util.Optional;

public interface IIncidentRepository {

    List<Incident> findIncidentsByUsername(String username);

    void add(Incident incident);

    Optional<Incident> findIncidentById(Long incidentId);

    List<Incident> findAllIncidents();

    void assignOperator(Incident incident);

    List<Incident> findByOperator(Long operatorId);

    void update(Incident incident);

}
