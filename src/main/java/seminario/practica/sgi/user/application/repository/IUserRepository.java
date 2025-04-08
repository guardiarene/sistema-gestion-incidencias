package seminario.practica.sgi.user.application.repository;

import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    void add(User user);

    void update(User user);

    void delete(Long id);

    List<User> findByRole(Role role);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
