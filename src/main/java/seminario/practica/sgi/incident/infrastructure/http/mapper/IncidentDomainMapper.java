package seminario.practica.sgi.incident.infrastructure.http.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.infrastructure.http.request.AssignIncidentRequest;
import seminario.practica.sgi.incident.infrastructure.http.request.CreateIncidentRequest;
import seminario.practica.sgi.incident.infrastructure.http.request.UpdateIncidentRequest;

@Component
public class IncidentDomainMapper {

    public Incident toDomain(CreateIncidentRequest createIncidentRequest) {
        Incident incident = new Incident();
        incident.setUserId(createIncidentRequest.getCustomer().getId());
        incident.setTitle(createIncidentRequest.getTitle());
        incident.setDescription(createIncidentRequest.getDescription());
        incident.setType(createIncidentRequest.getType());
        return incident;
    }

    public Incident toDomain(AssignIncidentRequest assignIncidentRequest) {
        Incident incident = new Incident();
        incident.setIncidentId(assignIncidentRequest.getIncidentId());
        incident.setOperatorId(assignIncidentRequest.getOperatorId());
        incident.setPriority(assignIncidentRequest.getPriority());
        return incident;
    }

    public Incident toDomain(UpdateIncidentRequest updateIncidentRequest) {
        Incident incident = new Incident();
        incident.setIncidentId(updateIncidentRequest.getIncidentId());
        incident.setStatus(updateIncidentRequest.getStatus());
        incident.setResolution(updateIncidentRequest.getResolution());
        return incident;
    }

}
