package br.com.fiap.vocatalk.dto;

import java.io.Serializable;

import br.com.fiap.vocatalk.models.TipoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoPagamentoDTO implements Serializable {

    @Schema(description = "ID do tipo de pagamento", example = "1")
    private Long id;

    @Schema(description = "Nome do tipo de pagamento", example = "Cartão de Crédito")
    private String nome;

    public TipoPagamentoDTO(TipoPagamento tipoPagamento) {
        this.id = tipoPagamento.getId();
        this.nome = tipoPagamento.getNome();
    }

}
