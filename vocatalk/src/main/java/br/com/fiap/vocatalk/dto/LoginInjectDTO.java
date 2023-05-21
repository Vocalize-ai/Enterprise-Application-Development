package br.com.fiap.vocatalk.dto;

import java.time.LocalDateTime;

import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.models.Login;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInjectDTO {

    private Long id;

    @NotBlank(message = "O e-mail tem que ser preenchido")
    @Email
    @Schema(example = "rm94898@fiap.com.br")
    private String email;

    @NotBlank(message = "A senha tem que ser preenchida")
    @Size(min = 8)
    @Schema(example = "321321312321")
    private String senha;

    @Schema(example = "2023-05-20T10:30:00")
    private LocalDateTime ultimoLogin;

    private Cliente cliente;

    public LoginInjectDTO(Login login) {
        this.id = login.getId();
        this.email = login.getEmail();
        this.senha = login.getSenha();
        this.ultimoLogin = login.getUltimoLogin();
        this.cliente = login.getCliente();
    }

}
