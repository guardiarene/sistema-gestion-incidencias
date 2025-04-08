package seminario.practica.sgi.incident.infrastructure.http.request;

import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.incident.domain.IncidentType;

@Getter
@Setter
public class CreateIncidentRequest {

    private IncidentCustomerRequest customer;

    private String title;

    private String description;

    private IncidentType type;

}
