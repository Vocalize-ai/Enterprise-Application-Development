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
import br.com.fiap.vocatalk.models.TipoPagamento;
import br.com.fiap.vocatalk.repositories.TipoPagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoPagamento")
@Tag(name = "Tipo de Pagamento", description = "API para gerenciamento de tipos de pagamento")
public class TipoPagamentoController {
    
    @Autowired
    TipoPagamentoRepository tipoPagamentoRepository;

    @GetMapping
    @Operation(summary = "Obter todos os tipos de pagamento", description = "Retorna uma lista com todos os tipos de pagamento existentes.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter todos os tipos de pagamento")
    public List<TipoPagamento> todosTiposPagamentos() {
        return tipoPagamentoRepository.findAll();
    }
    
    @PostMapping
    @Operation(summary = "Cadastrar tipo de pagamento", description = "Cadastra um novo tipo de pagamento com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Tipo de pagamento cadastrado com sucesso")
    public ResponseEntity<TipoPagamento> cadastrarTipoPagamento(@Valid @RequestBody TipoPagamento tipoPagamento){
        tipoPagamentoRepository.save(tipoPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPagamento);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter tipo de pagamento por ID", description = "Retorna os detalhes de um tipo de pagamento específico.")
    @ApiResponse(responseCode = "200", description = "Sucesso ao obter o tipo de pagamento")
    @ApiResponse(responseCode = "404", description = "Tipo de pagamento não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<TipoPagamento> encotraTipoPagamentoPorId(@PathVariable Long id){
        return ResponseEntity.ok(getTipoPagamento(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo de pagamento", description = "Atualiza os dados de um tipo de pagamento existente.")
    @ApiResponse(responseCode = "200", description = "Tipo de pagamento atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Tipo de pagamento não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<TipoPagamento> atualizaTipoPagamento(@Valid @PathVariable Long id, @RequestBody TipoPagamento tipoPagamento){
        getTipoPagamento(id);
        tipoPagamento.setId(id);
        tipoPagamentoRepository.save(tipoPagamento);
        return ResponseEntity.ok(tipoPagamento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover tipo de pagamento", description = "Remove um tipo de pagamento existente.")
    @ApiResponse(responseCode = "204", description = "Sucesso ao remover o tipo de pagamento")
    @ApiResponse(responseCode = "404", description = "Tipo de pagamento não encontrado", content = @io.swagger.v3.oas.annotations.media.Content())
    public ResponseEntity<TipoPagamento> removeTipoPagamento(@PathVariable Long id){
        tipoPagamentoRepository.delete(getTipoPagamento(id));
        return ResponseEntity.noContent().build();
    }
        
    private TipoPagamento getTipoPagamento(Long id) {
        return tipoPagamentoRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Tipo de pagamento não encontrado"));
    }
}
