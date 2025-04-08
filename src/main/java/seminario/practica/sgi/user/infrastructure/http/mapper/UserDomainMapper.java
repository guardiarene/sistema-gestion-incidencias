package seminario.practica.sgi.user.infrastructure.http.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.user.domain.User;
import seminario.practica.sgi.user.infrastructure.http.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDomainMapper {

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

    public List<UserResponse> toResponse(List<User> domainUsers) {
        List<UserResponse> userResponses = new ArrayList<>(domainUsers.size());
        for (User user : domainUsers) {
            userResponses.add(toResponse(user));
        }
        return userResponses;
    }

}
