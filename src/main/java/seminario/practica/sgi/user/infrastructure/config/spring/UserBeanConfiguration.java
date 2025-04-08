package seminario.practica.sgi.user.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import seminario.practica.sgi.user.application.service.CreateUserUseCaseService;
import seminario.practica.sgi.user.application.service.ListUserUseCaseService;
import seminario.practica.sgi.user.application.service.LoginUserUseCaseService;
import seminario.practica.sgi.user.application.service.usecase.ICreateUserUseCase;
import seminario.practica.sgi.user.application.service.usecase.IListUserUseCase;
import seminario.practica.sgi.user.infrastructure.config.spring.security.PasswordEncryptionService;
import seminario.practica.sgi.user.infrastructure.database.repository.UserRepository;

@Configuration
public class UserBeanConfiguration {

    @Bean
    public LoginUserUseCaseService loginUserUseCase(UserRepository userRepository) {
        return new LoginUserUseCaseService(userRepository);
    }

    @Bean
    public ICreateUserUseCase createUserUseCase(UserRepository userRepository,
                                                PasswordEncryptionService passwordEncryption) {
        return new CreateUserUseCaseService(userRepository, passwordEncryption);
    }

    @Bean
    public IListUserUseCase listUserUseCase(UserRepository userRepository) {
        return new ListUserUseCaseService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
