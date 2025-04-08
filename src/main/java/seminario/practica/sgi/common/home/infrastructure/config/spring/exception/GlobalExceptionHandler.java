package seminario.practica.sgi.common.home.infrastructure.config.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import seminario.practica.sgi.user.application.exception.UserAlreadyExistsException;
import seminario.practica.sgi.user.infrastructure.http.request.RegisterRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleUserAlreadyExists(UserAlreadyExistsException exception) {
        ModelAndView modelAndView = new ModelAndView("auth/register");
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("registerRequest", new RegisterRequest());
        return modelAndView;
    }

}
