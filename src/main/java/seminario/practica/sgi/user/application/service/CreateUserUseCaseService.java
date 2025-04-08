package seminario.practica.sgi.user.application.service;

import lombok.RequiredArgsConstructor;
import seminario.practica.sgi.common.home.infrastructure.config.spring.exception.ErrorMessage;
import seminario.practica.sgi.user.application.delegate.IPasswordEncryption;
import seminario.practica.sgi.user.application.exception.UserAlreadyExistsException;
import seminario.practica.sgi.user.application.repository.IUserRepository;
import seminario.practica.sgi.user.application.service.usecase.ICreateUserUseCase;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.domain.User;

@RequiredArgsConstructor
public class CreateUserUseCaseService implements ICreateUserUseCase {

    private final IUserRepository userRepository;

    private final IPasswordEncryption passwordEncryption;

    @Override
    public void add(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(ErrorMessage.USER_ALREADY_EXISTS.getMessage() + user.getEmail());
        }
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }
        user.setPassword(passwordEncryption.encrypt(user.getPassword()));
        userRepository.add(user);
    }

}
