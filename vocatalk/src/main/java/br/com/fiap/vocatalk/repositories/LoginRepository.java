package br.com.fiap.vocatalk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.vocatalk.models.Login;

public interface LoginRepository extends JpaRepository<Login,Long>{
    
    Optional<Login> findByEmail(String email);

}
