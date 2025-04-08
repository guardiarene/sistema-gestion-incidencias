package seminario.practica.sgi.incident.application.service.usecase;

import seminario.practica.sgi.incident.domain.Incident;

public interface IFindIncidentByIdUseCase {

    Incident findById(Long incidentId);

}
