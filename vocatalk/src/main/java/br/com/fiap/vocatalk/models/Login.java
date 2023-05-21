package br.com.fiap.vocatalk.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fiap.vocatalk.dto.LoginDTO;
import br.com.fiap.vocatalk.dto.LoginInjectDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="v_vt_login")
public class Login implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    @Schema(example = "1")
    private Long id;

    @NotBlank(message = "O e-mail tem que ser preenchido")
    @Email
    @Column(name = "ds_email", unique = true)
    @Schema(example = "luan.reis@rm.com")
    private String email;

    @NotBlank(message = "A senha tem que ser preenchida")
    @Size(min = 8)
    @Column(name="ds_senha")
    @Schema(example = "23131321321")
    private String senha;

    @NotNull(message = "A data não pode ser nula")
    @Column(name="dt_ultimo_login")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(example = "2023-05-20T10:30:00")
    private LocalDateTime ultimoLogin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;


    


    public Login(LoginDTO loginDTO) {
        this.id = loginDTO.getId();
        this.email = loginDTO.getEmail();
        this.ultimoLogin = loginDTO.getUltimoLogin();
        this.cliente = loginDTO.getCliente();
    }


    
    public Login(LoginInjectDTO loginDTO) {
        this.id = loginDTO.getId();
        this.email = loginDTO.getEmail();
        this.senha = loginDTO.getSenha();
        this.ultimoLogin = loginDTO.getUltimoLogin();
        this.cliente = loginDTO.getCliente();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String toString() {
        return "Login [id=" + id + ", email=" + email + ", senha=" + senha + ", ultimoLogin=" + ultimoLogin
                + ", cliente=" + cliente + "]";
    }


    

}
