package seminario.practica.sgi.common.home.infrastructure.config.spring.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        User userDetails = (User) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        String redirectUrl = switch (role) {
            case "ROLE_ADMIN" -> "/admin";
            case "ROLE_COORDINATOR" -> "/coordinator";
            case "ROLE_OPERATOR" -> "/operator";
            case "ROLE_CUSTOMER" -> "/customer";
            case null, default -> throw new IllegalStateException("Unexpected role: " + role);
        };
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
