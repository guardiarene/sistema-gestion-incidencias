package seminario.practica.sgi.user.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private Date createdAt;

}
