package seminario.practica.sgi.user.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import seminario.practica.sgi.user.domain.Role;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL")})
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private Timestamp createdAt;

    @Column(name = "SOFT_DELETED", nullable = false)
    private boolean softDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return !this.softDeleted;
    }

}
