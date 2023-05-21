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

import br.com.fiap.vocatalk.dto.ItemFaturaDTO;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.ItemFatura;
import br.com.fiap.vocatalk.services.ItemFaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/itemFatura")
@Tag(name = "ItemFatura", description = "API para gerenciamento de itens de fatura")
public class ItemFaturaController {

    @Autowired
    ItemFaturaService itemFaturaService;

    @GetMapping
    @Operation(summary = "Obter todos os itens de fatura", description = "Retorna uma lista com todos os itens de fatura existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todos os itens de fatura")
    public List<ItemFaturaDTO> todosItensFatura() {
        return itemFaturaService.getAll();
    }

    @PostMapping
    @Operation(summary = "Criar item de fatura", description = "Cria um novo item de fatura com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Item de fatura criado com sucesso")
    public ResponseEntity<ItemFaturaDTO> criarItemFatura(@RequestBody ItemFaturaDTO itemFaturaDTO) {
        ItemFaturaDTO itemFaturaSalvo = itemFaturaService.create(itemFaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemFaturaSalvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter item de fatura por ID", description = "Retorna os detalhes de um item de fatura específico.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter o item de fatura")
    @ApiResponse(responseCode = "404", description = "Item de fatura não encontrado")
    public ResponseEntity<ItemFaturaDTO> encontraItensFaturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemFaturaService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item de fatura", description = "Atualiza os dados de um item de fatura existente.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o item de fatura")
    @ApiResponse(responseCode = "404", description = "Item de fatura não encontrado")
    public ResponseEntity<ItemFaturaDTO> atualizaItensFatura(@PathVariable Long id,
            @RequestBody ItemFaturaDTO itensFatura) {
        ItemFaturaDTO itemFaturaDTO = itemFaturaService.update(id, itensFatura);
        return ResponseEntity.ok(itemFaturaDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover item de fatura", description = "Remove um item de fatura existente.")
    @ApiResponse(responseCode = "204", description = "Item de fatura removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Item de fatura não encontrado")
    public ResponseEntity<ItemFatura> removeItensFatura(@PathVariable Long id) {
        try {
            itemFaturaService.deletePorID(id);
            return ResponseEntity.noContent().build();
        } catch (RestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
