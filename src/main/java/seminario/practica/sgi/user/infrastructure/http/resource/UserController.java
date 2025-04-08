package seminario.practica.sgi.user.infrastructure.http.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import seminario.practica.sgi.common.userdata.IFindByEmail;
import seminario.practica.sgi.common.userdata.UserDataResponse;
import seminario.practica.sgi.user.application.service.usecase.ICreateUserUseCase;
import seminario.practica.sgi.user.application.service.usecase.IListUserUseCase;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.infrastructure.http.mapper.UserDomainMapper;
import seminario.practica.sgi.user.infrastructure.http.mapper.UserResponseMapper;
import seminario.practica.sgi.user.infrastructure.http.request.RegisterRequest;
import seminario.practica.sgi.user.infrastructure.http.response.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/")
public class UserController {

    private final IFindByEmail getUser;

    private final ICreateUserUseCase createUserUseCase;

    private final IListUserUseCase listUserUseCase;

    private final UserResponseMapper userResponseMapper;

    private final UserDomainMapper userDomainMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminPanel(Model model, Authentication authentication) {
        String viewName = loadUserPanel(model, authentication, "admin/admin_panel");
        List<UserResponse> coordinators = userDomainMapper.toResponse(listUserUseCase.findByRole(Role.COORDINATOR));
        model.addAttribute("coordinators", coordinators);
        return viewName;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/create_user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "common/create_user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create_user")
    public String createUser(@ModelAttribute("registerRequest")
                             @Valid RegisterRequest registerRequest,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "common/create_user";
        }
        createUserUseCase.add(userResponseMapper.toDomain(registerRequest));
        return "redirect:/admin";
    }

    /*@PreAuthorize("hasRole('COORDINATOR')")
    @GetMapping("/coordinator")
    public String coordinatorPanel(Model model, Authentication authentication) {
        return loadUserPanel(model, authentication, "coordinator/coordinator_panel");
    }*/

    /*@PreAuthorize("hasRole('OPERATOR')")
    @GetMapping("/operator")
    public String operatorPanel(Model model, Authentication authentication) {
        return loadUserPanel(model, authentication, "operator/operator_panel");
    }*/

    /*@PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer")
    public String customerPanel(Model model, Authentication authentication) {
        return loadUserPanel(model, authentication, "customer/customer_panel");
    }*/

    private String loadUserPanel(Model model, Authentication authentication, String viewName) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDataResponse userDataResponse = getUser.findByEmail(userDetails.getUsername());
        model.addAttribute("user", userDataResponse);
        return viewName;
    }

}
