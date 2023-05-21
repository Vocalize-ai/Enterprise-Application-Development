package br.com.fiap.vocatalk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.vocatalk.dto.FaturaDTO;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.services.FaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/faturas")
@Tag(name = "Fatura", description = "API para gerenciamento de faturas")
public class FaturaController {

    @Autowired
    FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping
    @Operation(summary = "Obter todas as faturas", description = "Retorna uma lista com todas as faturas existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todas as faturas")
    public ResponseEntity<List<FaturaDTO>> getAllFaturas() {
        List<FaturaDTO> faturas = faturaService.getAll();
        return ResponseEntity.ok(faturas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter fatura por ID", description = "Retorna os detalhes de uma fatura específica.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter a fatura")
    @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    public ResponseEntity<FaturaDTO> getFaturaById(@PathVariable Long id) {
        try {
            FaturaDTO fatura = faturaService.getById(id);
            return ResponseEntity.ok(fatura);
        } catch (RestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar fatura", description = "Cria uma nova fatura com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Fatura criada com sucesso")
    public ResponseEntity<FaturaDTO> createFatura(@RequestBody FaturaDTO faturaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faturaService.create(faturaDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar fatura", description = "Atualiza os dados de uma fatura existente.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar a fatura")
    @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    public ResponseEntity<FaturaDTO> updateFatura(@PathVariable Long id, @RequestBody FaturaDTO faturaDTO) {
        try {
            FaturaDTO faturaAtualizada = faturaService.update(id, faturaDTO);
            return ResponseEntity.ok(faturaAtualizada);
        } catch (RestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir fatura", description = "Exclui uma fatura existente.")
    @ApiResponse(responseCode = "204", description = "Fatura excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Fatura não encontrada")
    public ResponseEntity<Void> deleteFatura(@PathVariable Long id) {
        try {
            faturaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
