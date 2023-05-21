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
import br.com.fiap.vocatalk.models.ServicoAdicional;
import br.com.fiap.vocatalk.repositories.ServicoAdicionalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/servicoAdicional")
@Tag(name = "Serviço Adicional", description = "API para gerenciamento de serviços adicionais")
public class ServicoAdicionalController {
    
    @Autowired
    ServicoAdicionalRepository servicoAdicionalRepository;
    
    @GetMapping
    @Operation(summary = "Obter todos os serviços adicionais", description = "Retorna uma lista com todos os serviços adicionais existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todos os serviços adicionais")
    public List<ServicoAdicional> todosServicosAdicionais() {
        return servicoAdicionalRepository.findAll();
    }
    
    @PostMapping
    @Operation(summary = "Cadastrar serviço adicional", description = "Cadastra um novo serviço adicional com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Serviço adicional cadastrado com sucesso")
    public ResponseEntity<ServicoAdicional> cadastrarServicoAdiconal(@Valid @RequestBody ServicoAdicional servicoAdicional){
        servicoAdicionalRepository.save(servicoAdicional);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoAdicional);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter serviço adicional por ID", description = "Retorna os detalhes de um serviço adicional específico.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter o serviço adicional")
    @ApiResponse(responseCode = "404", description = "Serviço adicional não encontrado")
    public ResponseEntity<ServicoAdicional> encotraServicoAdiconalPorId(@PathVariable Long id){
        return ResponseEntity.ok(getServicoAdicional(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar serviço adicional", description = "Atualiza os dados de um serviço adicional existente.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o serviço adicional")
    @ApiResponse(responseCode = "404", description = "Serviço adicional não encontrado")
    public ResponseEntity<ServicoAdicional> atualizaSevicoAdicional(@Valid @PathVariable Long id, @RequestBody ServicoAdicional servicoAdicional){
        getServicoAdicional(id);
        servicoAdicional.setId(id);
        servicoAdicionalRepository.save(servicoAdicional);
        return ResponseEntity.ok(servicoAdicional);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover serviço adicional", description = "Remove um serviço adicional existente.")
    @ApiResponse(responseCode = "204", description = "Sucesso ao remover o serviço adicional")
    @ApiResponse(responseCode = "404", description = "Serviço adicional não encontrado")
    public ResponseEntity<ServicoAdicional> removeServicoAdicional(@PathVariable Long id){
        servicoAdicionalRepository.delete(getServicoAdicional(id));
        return ResponseEntity.noContent().build();
    }
        
    private ServicoAdicional getServicoAdicional(Long id) {
        return servicoAdicionalRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Serviço adicional não encontrado"));
    }
}
