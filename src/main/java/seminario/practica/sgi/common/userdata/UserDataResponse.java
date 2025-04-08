package seminario.practica.sgi.common.userdata;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDataResponse {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

}
