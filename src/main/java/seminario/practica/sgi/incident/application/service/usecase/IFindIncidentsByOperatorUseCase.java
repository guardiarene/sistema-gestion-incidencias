package seminario.practica.sgi.incident.application.service.usecase;

import seminario.practica.sgi.incident.domain.Incident;

import java.util.List;

public interface IFindIncidentsByOperatorUseCase {

    List<Incident> findByOperator(Long id);

}
