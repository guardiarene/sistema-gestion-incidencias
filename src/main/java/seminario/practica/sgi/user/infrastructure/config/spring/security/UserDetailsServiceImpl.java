package seminario.practica.sgi.user.infrastructure.config.spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import seminario.practica.sgi.common.home.infrastructure.config.spring.exception.ErrorMessage;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;
import seminario.practica.sgi.user.infrastructure.database.repository.abstraction.IUserSpringRepository;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserSpringRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email);
        }
        UserEntity userEntity = userOptional.get();
        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name())));
    }

}
