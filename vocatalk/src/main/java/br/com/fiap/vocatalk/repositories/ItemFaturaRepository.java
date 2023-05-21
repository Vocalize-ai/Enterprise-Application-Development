package br.com.fiap.vocatalk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.vocatalk.models.ItemFatura;

public interface ItemFaturaRepository extends JpaRepository<ItemFatura, Long>{
    
}
