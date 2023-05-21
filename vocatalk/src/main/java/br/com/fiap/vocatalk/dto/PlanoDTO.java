package br.com.fiap.vocatalk.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.models.Plano;
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
public class PlanoDTO {

    @Schema(description = "ID do plano", example = "1")
    private Long id;

    @Schema(description = "Nome do plano", example = "Plano Básico")
    private String nome;

    @Schema(description = "Quantidade de minutos", example = "1000")
    private int qtdMinutos;

    @Schema(description = "Quantidade de internet (em MB)", example = "5000")
    private int qtdInternet;

    @Schema(description = "Valor mensal do plano", example = "99.90")
    private BigDecimal valorMensal;

    @Schema(description = "Itens da fatura associados ao plano")
    private List<ItemFatura> itemFatura;

    @Schema(description = "Descrição do plano", example = "Plano com minutos ilimitados e 5GB de internet")
    private String descricao;

    public PlanoDTO(Plano plano) {
        this.id = plano.getId();
        this.nome = plano.getNome();
        this.qtdMinutos = plano.getQtdMinutos();
        this.qtdInternet = plano.getQtdInternet();
        this.valorMensal = plano.getValorMensal();
        this.descricao = plano.getDescricao();
    }
}
