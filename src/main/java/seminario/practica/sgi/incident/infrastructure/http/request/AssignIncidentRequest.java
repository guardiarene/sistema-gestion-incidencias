package seminario.practica.sgi.incident.infrastructure.http.request;

import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.incident.domain.IncidentPriority;

@Getter
@Setter
public class AssignIncidentRequest {

    private Long incidentId;

    private Long operatorId;

    private IncidentPriority priority;

}
