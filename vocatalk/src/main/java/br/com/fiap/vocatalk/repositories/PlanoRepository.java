package br.com.fiap.vocatalk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.vocatalk.models.Plano;

public interface PlanoRepository extends JpaRepository<Plano,Long>{
    
}
