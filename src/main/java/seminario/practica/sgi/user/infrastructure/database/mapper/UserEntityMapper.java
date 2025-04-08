package seminario.practica.sgi.user.infrastructure.database.mapper;

import org.springframework.stereotype.Component;
import seminario.practica.sgi.user.domain.User;
import seminario.practica.sgi.user.infrastructure.database.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User userDomain) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDomain.getId());
        userEntity.setFirstName(userDomain.getFirstName());
        userEntity.setLastName(userDomain.getLastName());
        userEntity.setPassword(userDomain.getPassword());
        userEntity.setEmail(userDomain.getEmail());
        userEntity.setRole(userDomain.getRole());
        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getUserId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getUsername());
        user.setRole(userEntity.getRole());
        user.setCreatedAt(userEntity.getCreatedAt());
        return user;
    }

    public List<User> toDomain(List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            users.add(toDomain(userEntity));
        }
        return users;
    }

}
