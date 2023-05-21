package br.com.fiap.vocatalk.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    @Schema(example = "Erro de teste")
    private String message;

    @Schema(example = "400")
    private int errorCode;

}
