package seminario.practica.sgi.user.infrastructure.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import seminario.practica.sgi.user.domain.Role;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;

    private Role role;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password must be valid")
    @Size(min = 5, max = 15, message = "Password must be between 5 and 12 characters")
    private String password;

}
