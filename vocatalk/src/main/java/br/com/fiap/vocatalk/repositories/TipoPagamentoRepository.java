package br.com.fiap.vocatalk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.vocatalk.models.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento,Long>{
    
}
