package seminario.practica.sgi.incident.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    private Long incidentId;

    private String title;

    private String description;

    private Timestamp createdAt;

    private IncidentStatus status = IncidentStatus.UNASSIGNED;

    private IncidentPriority priority = IncidentPriority.UNDEFINED;

    private IncidentType type;

    private Long operatorId;

    private Long userId;

    private String resolution;

}
