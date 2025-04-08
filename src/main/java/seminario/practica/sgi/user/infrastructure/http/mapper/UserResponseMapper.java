package seminario.practica.sgi.user.infrastructure.http.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.user.domain.User;
import seminario.practica.sgi.user.infrastructure.http.request.RegisterRequest;

@Component
public class UserResponseMapper {

    public User toDomain(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole(registerRequest.getRole());
        return user;
    }

}
