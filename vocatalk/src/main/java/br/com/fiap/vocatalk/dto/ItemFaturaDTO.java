package br.com.fiap.vocatalk.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.models.ServicoAdicional;
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
public class ItemFaturaDTO {
    
    @Schema(description = "ID do item da fatura", example = "1")
    private Long id;

    @Schema(description = "Status do item da fatura", example = "A")
    private Character status;

    @Schema(description = "Data de adição do item na fatura", example = "2023-05-20T10:30:00")
    private LocalDateTime adicionado;

    @Schema(description = "Informações do plano")
    private Plano plano;

    @Schema(description = "Lista de serviços adicionais")
    private List<ServicoAdicional> servicosAdicionais = new ArrayList<>();

    public ItemFaturaDTO(ItemFatura itemFatura) {
        this.id = itemFatura.getId();
        this.status = itemFatura.getStatus();
        this.adicionado = itemFatura.getAdicionado();
        this.plano = itemFatura.getPlano();
        this.servicosAdicionais = itemFatura.getServicosAdicionais();
    }




    

}
