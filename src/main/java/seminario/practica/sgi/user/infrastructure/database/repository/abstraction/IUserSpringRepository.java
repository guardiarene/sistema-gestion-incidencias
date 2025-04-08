package seminario.practica.sgi.user.infrastructure.database.repository.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserSpringRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByRole(Role role);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}
