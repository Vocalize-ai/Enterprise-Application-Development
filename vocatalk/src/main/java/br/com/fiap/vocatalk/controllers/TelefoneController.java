package br.com.fiap.vocatalk.controllers;

import java.util.ArrayList;
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

import br.com.fiap.vocatalk.dto.ClienteDTO;
import br.com.fiap.vocatalk.dto.TelefoneDTO;
import br.com.fiap.vocatalk.exceptions.RestNotFoundException;
import br.com.fiap.vocatalk.models.Cliente;
import br.com.fiap.vocatalk.models.Telefone;
import br.com.fiap.vocatalk.repositories.TelefoneRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
@Tag(name = "Telefone", description = "API para gerenciamento de telefones de contato")
public class TelefoneController {

    @Autowired
    TelefoneRepository repository;

    @GetMapping
    @Operation(summary = "Obter todos os telefones de contato", description = "Retorna uma lista com todos os telefones de contato existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todos os telefones de contato")
    public List<TelefoneDTO> getAll() {
        List<Telefone> telefones = repository.findAll();
        List<TelefoneDTO> telefonesDTO = new ArrayList<>();

        for (Telefone telefone : telefones) {
            TelefoneDTO telefoneDTO = new TelefoneDTO();
            telefoneDTO.setId(telefone.getId());
            telefoneDTO.setDdd(telefone.getDdd());
            telefoneDTO.setTelefone(telefone.getTelefone());

            Cliente cliente = telefone.getCliente();
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setCpf(cliente.getCpf());
            clienteDTO.setDataCadastro(cliente.getDataCadastro());

            telefoneDTO.setCliente(clienteDTO);

            telefonesDTO.add(telefoneDTO);
        }

        return telefonesDTO;
    }
    
    @PostMapping
    @Operation(summary = "Cadastrar telefone de contato", description = "Cadastra um novo telefone de contato com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Telefone de contato cadastrado com sucesso")
    public ResponseEntity<Telefone> cadastrarTelefoneContato(@RequestBody @Valid Telefone telefoneContato) {
        repository.save(telefoneContato);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneContato);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter telefone de contato por ID", description = "Retorna os detalhes de um telefone de contato específico.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter o telefone de contato")
    @ApiResponse(responseCode = "404", description = "Telefone de contato não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<Telefone> encotraTelefoneContatoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getTelefoneContato(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar telefone de contato", description = "Atualiza os dados de um telefone de contato existente.")
    @ApiResponse(responseCode = "200", description = "Telefone de contato atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone de contato não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<Telefone> atualizaTelefoneContato(@Valid @PathVariable Long id,
            @RequestBody Telefone telefoneContato) {
        getTelefoneContato(id);
        telefoneContato.setId(id);
        repository.save(telefoneContato);
        return ResponseEntity.ok(telefoneContato);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover telefone de contato", description = "Remove um telefone de contato existente.")
    @ApiResponse(responseCode = "204", description = "Sucesso ao remover o telefone de contato")
    @ApiResponse(responseCode = "404", description = "Telefone de contato não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<Telefone> removeTelefoneContato(@PathVariable Long id) {
        repository.delete(getTelefoneContato(id));
        return ResponseEntity.noContent().build();
    }

    private Telefone getTelefoneContato(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Telefone de contato não encontrado"));
    }
}
