package br.com.fiap.vocatalk.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaDTO {

    @Schema(description = "ID da fatura", example = "1")
    private Long id;

    @Schema(description = "Valor da fatura", example = "100.50")
    private BigDecimal valor;

    @Schema(description = "Data de vencimento da fatura", example = "2023-05-20T10:30:00")
    private LocalDateTime dataVencimento;

    @Schema(description = "Data de pagamento da fatura", example = "2023-05-25T15:45:00")
    private LocalDateTime dataPagamento;

    @Schema(description = "Informações do cliente")
    private ClienteDTO cliente;

    @Schema(description = "Informações do tipo de pagamento")
    private TipoPagamentoDTO tipoPagamento;

    @Schema(description = "Informações do item da fatura")
    private ItemFaturaDTO itemFatura;

}
