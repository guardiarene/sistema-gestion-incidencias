package seminario.practica.sgi.user.infrastructure.config.spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import seminario.practica.sgi.user.application.delegate.IPasswordEncryption;

@RequiredArgsConstructor
@Component
public class PasswordEncryptionService implements IPasswordEncryption {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

}
