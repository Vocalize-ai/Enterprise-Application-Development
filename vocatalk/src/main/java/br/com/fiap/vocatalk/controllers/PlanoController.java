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

import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.Plano;
import br.com.fiap.vocatalk.repositories.PlanoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plano")
@Tag(name = "Plano", description = "API para gerenciamento de planos")
public class PlanoController {
 
    @Autowired
    PlanoRepository planoRepository;

    @GetMapping
    @Operation(summary = "Obter todos os planos", description = "Retorna uma lista com todos os planos existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todos os planos")
    public List<Plano> todosPlanos() {
        return planoRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar plano", description = "Cadastra um novo plano com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Plano cadastrado com sucesso")
    public ResponseEntity<Plano> cadastraPlano(@RequestBody @Valid Plano plano) {
        planoRepository.save(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(plano);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter plano por ID", description = "Retorna os detalhes de um plano específico.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter o plano")
    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    public ResponseEntity<Plano> encontraPlanoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getPlano(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover plano", description = "Remove um plano existente.")
    @ApiResponse(responseCode = "204", description = "Plano removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    public ResponseEntity<Plano> removePlano(@PathVariable Long id) {
        planoRepository.delete(getPlano(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar plano", description = "Atualiza os dados de um plano existente.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o plano")
    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    public ResponseEntity<Plano> atualizaPlano(@PathVariable Long id, @RequestBody @Valid Plano plano) {
        Plano existingPlano = getPlano(id);
        existingPlano.setId(id);
        planoRepository.save(existingPlano);
        return ResponseEntity.ok(existingPlano);
    }

    private Plano getPlano(Long id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Plano não encontrado"));
    }
}
