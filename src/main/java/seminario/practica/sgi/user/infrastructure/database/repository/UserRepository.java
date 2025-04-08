package seminario.practica.sgi.user.infrastructure.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seminario.practica.sgi.user.application.repository.IUserRepository;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.domain.User;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;
import seminario.practica.sgi.user.infrastructure.database.mapper.UserEntityMapper;
import seminario.practica.sgi.user.infrastructure.database.repository.abstraction.IUserSpringRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final IUserSpringRepository userSpringRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public void add(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userSpringRepository.save(userEntity);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findByRole(Role role) {
        return userEntityMapper.toDomain(userSpringRepository.findByRole(role));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userSpringRepository.findByEmail(email).map(userEntityMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userSpringRepository.existsByEmail(email);
    }

}
