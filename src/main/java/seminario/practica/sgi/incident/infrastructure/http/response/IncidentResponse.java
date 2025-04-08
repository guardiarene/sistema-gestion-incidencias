package seminario.practica.sgi.incident.infrastructure.http.response;

import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.incident.domain.IncidentPriority;
import seminario.practica.sgi.incident.domain.IncidentStatus;
import seminario.practica.sgi.incident.domain.IncidentType;

import java.sql.Timestamp;

@Getter
@Setter
public class IncidentResponse {

    private Long incidentId;

    private String title;

    private String description;

    private IncidentStatus status;

    private IncidentPriority priority;

    private IncidentType type;

    private Timestamp createdAt;

    private String resolution;

}
