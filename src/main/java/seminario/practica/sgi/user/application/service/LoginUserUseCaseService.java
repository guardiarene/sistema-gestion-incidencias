package seminario.practica.sgi.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import seminario.practica.sgi.common.home.infrastructure.config.spring.exception.ErrorMessage;
import seminario.practica.sgi.common.userdata.IFindByEmail;
import seminario.practica.sgi.common.userdata.UserDataResponse;
import seminario.practica.sgi.user.application.repository.IUserRepository;
import seminario.practica.sgi.user.domain.User;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginUserUseCaseService implements IFindByEmail {

    private final IUserRepository userRepository;

    @Override
    public UserDataResponse findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserDataResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build();
        } else {
            throw new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email);
        }
    }

}
