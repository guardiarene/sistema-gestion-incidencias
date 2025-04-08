package seminario.practica.sgi.user.infrastructure.http.response;

import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.user.domain.Role;

import java.util.Date;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Date createdAt;

}
