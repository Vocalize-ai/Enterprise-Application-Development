package br.com.fiap.vocatalk.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContratarPlanoDTO {

    @Schema(description = "ID do cliente", example = "1")
    private Long cliente;

    @Schema(description = "ID do plano", example = "1")
    private Long plano;

    @Schema(description = "ID do tipo de pagamento", example = "1")
    private Long tipoPagamento;

}
