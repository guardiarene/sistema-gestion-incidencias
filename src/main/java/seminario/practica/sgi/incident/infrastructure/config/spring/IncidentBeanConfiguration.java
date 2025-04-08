package seminario.practica.sgi.incident.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seminario.practica.sgi.incident.application.repository.IIncidentRepository;
import seminario.practica.sgi.incident.application.service.*;
import seminario.practica.sgi.incident.application.service.usecase.*;

@Configuration
public class IncidentBeanConfiguration {

    @Bean
    public IFindIncidentsByUsernameUseCase listIncidentsByUsernameUseCase(IIncidentRepository incidentRepository) {
        return new FindIncidentsByUsernameUseCaseService(incidentRepository);
    }

    @Bean
    public ICreateIncidentUseCase createIncidentUseCase(IIncidentRepository incidentRepository) {
        return new CreateIncidentUseCaseService(incidentRepository);
    }

    @Bean
    public IFindIncidentByIdUseCase findIncidentByIdUseCase(IIncidentRepository incidentRepository) {
        return new FindIncidentByIdUseCaseService(incidentRepository);
    }

    @Bean
    public IFindIncidentsUseCase findIncidentsUseCase(IIncidentRepository incidentRepository) {
        return new FindIncidentsUseCaseService(incidentRepository);
    }

    @Bean
    public IAssignOperatorUseCase assignOperatorUseCase(IIncidentRepository incidentRepository) {
        return new AssignOperatorUseCaseService(incidentRepository);
    }

    @Bean
    public IFindIncidentsByOperatorUseCase findIncidentsByOperatorUseCase(IIncidentRepository incidentRepository) {
        return new FindIncidentByOperatorUseCaseService(incidentRepository);
    }

    @Bean
    IUpdateIncidentUseCase updateIncidentUseCase(IIncidentRepository incidentRepository) {
        return new UpdateIncidentUseCaseService(incidentRepository);
    }

}
