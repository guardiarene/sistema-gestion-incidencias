package seminario.practica.sgi.user.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.user.application.repository.IUserRepository;
import seminario.practica.sgi.user.application.service.usecase.IListUserUseCase;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.domain.User;

import java.util.List;

@RequiredArgsConstructor
public class ListUserUseCaseService implements IListUserUseCase {

    private final IUserRepository userRepository;

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

}
