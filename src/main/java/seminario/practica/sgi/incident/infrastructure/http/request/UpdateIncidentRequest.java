package seminario.practica.sgi.incident.infrastructure.http.request;

import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.incident.domain.IncidentStatus;

@Getter
@Setter
public class UpdateIncidentRequest {

    private Long incidentId;

    private IncidentStatus status;

    private String resolution;

}
