package br.com.fiap.vocatalk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.vocatalk.models.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura,Long>{
    
}
