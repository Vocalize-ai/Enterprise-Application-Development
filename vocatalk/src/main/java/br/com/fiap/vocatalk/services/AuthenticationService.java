package br.com.fiap.vocatalk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.repositories.LoginRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado"));
    }


    
}
