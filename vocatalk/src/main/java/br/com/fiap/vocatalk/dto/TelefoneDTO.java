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
public class TelefoneDTO {
    
    @Schema(description = "ID do telefone", example = "1")
    private Long id;

    @Schema(description = "DDD do telefone", example = "11")
    private String ddd;

    @Schema(description = "Número de telefone", example = "999999999")
    private String telefone;

    @Schema(description = "Cliente associado ao telefone")
    private ClienteDTO cliente;

}
