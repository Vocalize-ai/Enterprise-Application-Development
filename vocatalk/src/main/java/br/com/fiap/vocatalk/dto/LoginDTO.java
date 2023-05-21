package br.com.fiap.vocatalk.dto;

import java.time.LocalDateTime;

import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.models.Login;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Schema(description = "id login dto", example = "1")
    private Long id;

    @Schema(description = "Email", example = "Luan.Reis@yteste.com")
    private String email;

    @Schema(description = "ultimo login feito pelo cliente", example = "2023-05-20T10:30:00")
    private LocalDateTime ultimoLogin;

    @Schema(description = "Cliente")
    private Cliente cliente;

    public LoginDTO(Login login) {
        this.id = login.getId();
        this.email = login.getEmail();
        this.ultimoLogin = login.getUltimoLogin();
        this.cliente = login.getCliente();
    }

}
