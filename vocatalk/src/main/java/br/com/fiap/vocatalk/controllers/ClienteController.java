package br.com.fiap.vocatalk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.repositories.ClienteRepository;
import br.com.fiap.vocatalk.repositories.TelefoneRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "API para gerenciamento de clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    TelefoneRepository repositoryTelefone;

    @GetMapping
    @Operation(
        summary = "Listar clientes",
        description = "Obter a lista de todos os clientes cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Sucesso ao listar clientes")
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @PostMapping()
    @Operation(
        summary = "Cadastrar cliente",
        description = "Cadastrar um novo cliente"
    )
    @ApiResponse(responseCode = "200", description = "Sucesso ao cadastrar cliente")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obter cliente por ID",
        description = "Obter as informações de um cliente pelo seu ID"
    )
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter cliente")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getCliente(id));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar cliente",
        description = "Atualizar as informações de um cliente existente"
    )
    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar cliente")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        getCliente(id);
        cliente.setId(id);
        repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover cliente",
        description = "Remover um cliente existente"
    )
    @ApiResponse(responseCode = "204", description = "Sucesso ao remover cliente")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    public ResponseEntity<Cliente> remover(@PathVariable Long id) {
        repository.delete(getCliente(id));
        return ResponseEntity.noContent().build();
    }

    private Cliente getCliente(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Cliente não encontrado"));
    }
}
