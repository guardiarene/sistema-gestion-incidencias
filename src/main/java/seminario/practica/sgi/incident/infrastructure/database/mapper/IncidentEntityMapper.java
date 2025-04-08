package seminario.practica.sgi.incident.infrastructure.database.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.incident.domain.Incident;
import seminario.practica.sgi.incident.infrastructure.database.entity.IncidentEntity;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncidentEntityMapper {

    public IncidentEntity toEntityUpdate(Incident incident) {
        IncidentEntity incidentEntity = new IncidentEntity();
        incidentEntity.setIncidentId(incident.getIncidentId());
        incidentEntity.setStatus(incident.getStatus());
        incidentEntity.setResolution(incident.getResolution());
        return incidentEntity;
    }

    public IncidentEntity toEntityAssign(Incident incident) {
        IncidentEntity incidentEntity = new IncidentEntity();
        UserEntity operator = new UserEntity();
        operator.setUserId(incident.getOperatorId());
        incidentEntity.setIncidentId(incident.getIncidentId());
        incidentEntity.setOperator(operator);
        incidentEntity.setStatus(incident.getStatus());
        incidentEntity.setPriority(incident.getPriority());
        return incidentEntity;
    }

    public IncidentEntity toEntity(Incident incident) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(incident.getUserId());
        IncidentEntity incidentEntity = new IncidentEntity();
        incidentEntity.setUser(userEntity);
        incidentEntity.setTitle(incident.getTitle());
        incidentEntity.setDescription(incident.getDescription());
        incidentEntity.setType(incident.getType());
        return incidentEntity;
    }

    public Incident toDomain(IncidentEntity incidentEntity) {
        Incident incident = new Incident();
        incident.setIncidentId(incidentEntity.getIncidentId());
        incident.setTitle(incidentEntity.getTitle());
        incident.setDescription(incidentEntity.getDescription());
        incident.setCreatedAt(incidentEntity.getCreatedAt());
        incident.setStatus(incidentEntity.getStatus());
        incident.setPriority(incidentEntity.getPriority());
        incident.setType(incidentEntity.getType());
        incident.setCreatedAt(incidentEntity.getCreatedAt());
        incident.setResolution(incidentEntity.getResolution());
        incident.setUserId(incidentEntity.getUser().getUserId());
        return incident;
    }

    public List<Incident> toDomain(List<IncidentEntity> incidentEntities) {
        List<Incident> incidents = new ArrayList<>(incidentEntities.size());
        for (IncidentEntity incidentEntity : incidentEntities) {
            incidents.add(toDomain(incidentEntity));
        }
        return incidents;
    }

}
