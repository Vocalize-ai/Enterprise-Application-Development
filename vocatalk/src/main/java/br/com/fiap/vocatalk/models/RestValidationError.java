package br.com.fiap.vocatalk.models;

import io.swagger.v3.oas.annotations.media.Schema;

public record RestValidationError(
                @Schema(example = "nome") String field,
                @Schema(description = "este campo está errado") String mensagem) {

}
