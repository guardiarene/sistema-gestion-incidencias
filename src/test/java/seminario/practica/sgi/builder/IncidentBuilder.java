package seminario.practica.sgi.builder;

import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.domain.IncidentStatus;
import seminario.practica.sgi.incident.domain.IncidentType;

public class IncidentBuilder {

    public static Incident random() {
        return new Incident(1L, "Title", "Description", null, IncidentStatus.UNASSIGNED, null, IncidentType.OPERATIONAL_QUERIES, null, 1L, null);
    }

    public static Incident assignedIncident() {
        return new Incident(1L, "Title", "Description", null, IncidentStatus.ASSIGNED, null, IncidentType.OPERATIONAL_QUERIES, 1L, 1L, null);
    }

}
