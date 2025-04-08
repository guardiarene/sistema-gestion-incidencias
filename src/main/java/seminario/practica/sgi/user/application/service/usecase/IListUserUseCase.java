package seminario.practica.sgi.user.application.service.usecase;

import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.domain.User;

import java.util.List;

public interface IListUserUseCase {

    List<User> findByRole(Role role);

}
