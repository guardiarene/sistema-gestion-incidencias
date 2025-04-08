package seminario.practica.sgi.incident.infrastructure.http.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.infrastructure.http.response.IncidentResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncidentResponseMapper {

    public IncidentResponse toDomain(Incident incident) {
        IncidentResponse incidentResponse = new IncidentResponse();
        incidentResponse.setIncidentId(incident.getIncidentId());
        incidentResponse.setTitle(incident.getTitle());
        incidentResponse.setDescription(incident.getDescription());
        incidentResponse.setStatus(incident.getStatus());
        incidentResponse.setPriority(incident.getPriority());
        incidentResponse.setType(incident.getType());
        incidentResponse.setCreatedAt(incident.getCreatedAt());
        incidentResponse.setResolution(incident.getResolution());
        return incidentResponse;
    }

    public List<IncidentResponse> toDomain(List<Incident> incidents) {
        List<IncidentResponse> responses = new ArrayList<>(incidents.size());
        for (Incident incident : incidents) {
            responses.add(toDomain(incident));
        }
        return responses;
    }

}
