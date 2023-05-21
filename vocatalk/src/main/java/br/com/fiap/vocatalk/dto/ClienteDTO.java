package br.com.fiap.vocatalk.dto;

import java.time.LocalDateTime;

import br.com.fiap.vocatalk.models.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    @Schema(description = "ID do cliente")
    private Long id;

    @Schema(description = "Nome do cliente", example = "Luan Reis")
    private String nome;

    @Schema(description = "CPF do cliente", example = "4458745878")
    private String cpf;

    @Schema(description = "Data de cadastro do cliente")
    private LocalDateTime dataCadastro;

    // private List<Fatura> fatura;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataCadastro = cliente.getDataCadastro();
        // this.fatura = cliente.getFatura();
    }

    @Override
    public String toString() {
        return "ClienteDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro
                + "]";
    }

}
