package seminario.practica.sgi.incident.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import seminario.practica.sgi.incident.domain.IncidentPriority;
import seminario.practica.sgi.incident.domain.IncidentStatus;
import seminario.practica.sgi.incident.domain.IncidentType;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "INCIDENTS")
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INCIDENT_ID")
    private Long incidentId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private IncidentStatus status = IncidentStatus.UNASSIGNED;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private IncidentPriority priority = IncidentPriority.UNDEFINED;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private IncidentType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "OPERATOR_ID", referencedColumnName = "USER_ID")
    private UserEntity operator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "USER_ID")
    private UserEntity user;

    @Column(name = "RESOLUTION")
    private String resolution = "----------";

}
