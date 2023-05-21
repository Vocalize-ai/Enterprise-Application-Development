package br.com.fiap.vocatalk.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.fiap.vocatalk.models.ItemFatura;
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
public class ServicoAdicionalDTO {

    @Schema(description = "ID do serviço adicional", example = "1")
    private Long id;

    @Schema(description = "Nome do serviço adicional", example = "Serviço de SMS")
    private String nome;

    @Schema(description = "Valor do serviço adicional", example = "9.99")
    private BigDecimal valor;

    @Schema(description = "Descrição do serviço adicional", example = "Pacote de SMS ilimitados")
    private String descricao;

    @Schema(description = "Itens da fatura associados ao serviço adicional")
    private List<ItemFatura> itensFatura;

}
