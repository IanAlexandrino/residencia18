package org.passwordrecovery.ap002.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "UserName não pode ser nulo nem vazio!")
    @Size(min = 3, max = 20, message = "UserName não pode ter menos de 3 caracteres nem mais de 20!")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email deve ser válido!")
    @NotBlank(message = "Email não pode ser nulo nem vazio!")
    @Size(min = 14, max = 50, message = "Email não pode ter menos de 14 caracteres nem mais de 50!")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NotBlank(message = "PassWord não pode ser nulo nem vazio!")
    @Size(min = 8, max = 40, message = "PassWord não pode ter menos de 8 caracteres nem mais de 40!")
    private String password;

    @Column(name = "role", nullable = false)
    @NotBlank(message = "Role não pode ser nulo nem vazio!")
    private UserRole role;

    public User(String username, String email, String password, UserRole userRole){
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
