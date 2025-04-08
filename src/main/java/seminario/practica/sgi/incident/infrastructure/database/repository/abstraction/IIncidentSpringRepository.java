package seminario.practica.sgi.incident.infrastructure.database.repository.abstraction;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seminario.practica.sgi.incident.infrastructure.database.entity.IncidentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface IIncidentSpringRepository extends JpaRepository<IncidentEntity, Long> {

    @Query("SELECT i FROM IncidentEntity i WHERE i.user.email = ?1")
    List<IncidentEntity> findByUsername(String username);

    Optional<IncidentEntity> findIncidentByIncidentId(Long incidentId);

    @Modifying
    @Transactional
    @Query("UPDATE IncidentEntity i SET i.operator = :#{#incident.operator}, i.priority = :#{#incident.priority}, i.status = :#{#incident.status} WHERE i.incidentId = :#{#incident.incidentId}")
    void assignOperator(@Param("incident") IncidentEntity incidentEntity);

    List<IncidentEntity> findByOperator_UserId(Long operatorId);

    @Modifying
    @Transactional
    @Query("UPDATE IncidentEntity i SET i.status = :#{#incident.status}, i.resolution = :#{#incident.resolution} WHERE i.incidentId = :#{#incident.incidentId}")
    void update(@Param("incident") IncidentEntity incidentEntity);

}
