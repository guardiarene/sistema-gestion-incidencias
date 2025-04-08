package seminario.practica.sgi.user.infrastructure.http.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import seminario.practica.sgi.user.application.service.usecase.ICreateUserUseCase;
import seminario.practica.sgi.user.infrastructure.http.mapper.UserResponseMapper;
import seminario.practica.sgi.user.infrastructure.http.request.RegisterRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final ICreateUserUseCase createUserUseCase;

    private final UserResponseMapper userResponseMapper;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerRequest")
                           @Valid RegisterRequest registerRequest,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        createUserUseCase.add(userResponseMapper.toDomain(registerRequest));
        return "redirect:/auth/login?registered";
    }

}
