package br.com.fiap.vocatalk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.vocatalk.dto.ContratarPlanoDTO;
import br.com.fiap.vocatalk.dto.FaturaDTO;
import br.com.fiap.vocatalk.exceptions.CustomerHasPlanException;
import br.com.fiap.vocatalk.models.ErrorResponse;
import br.com.fiap.vocatalk.services.ContratarPlanoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Contratar Plano", description = "API para contratação de planos")
public class ContratarPlanoController {

    @Autowired
    ContratarPlanoService contratarPlanoService;

    @PostMapping("/contratar-plano")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Plano contratado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FaturaDTO.class))}),
        @ApiResponse(responseCode = "400", description = "Cliente já possui um plano contratado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
})
    public ResponseEntity<?> contratarPlano(@RequestBody ContratarPlanoDTO contratarPlanoDTO) {
        try {
            FaturaDTO fatura = contratarPlanoService.contratarPlano(contratarPlanoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(fatura);
        } catch (CustomerHasPlanException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getMessage());
            errorResponse.setErrorCode(400);
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
}
