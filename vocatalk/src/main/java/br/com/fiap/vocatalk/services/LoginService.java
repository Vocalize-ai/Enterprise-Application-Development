package br.com.fiap.vocatalk.services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.vocatalk.dto.LoginDTO;
import br.com.fiap.vocatalk.dto.LoginInjectDTO;
import br.com.fiap.vocatalk.models.Credencial;
import br.com.fiap.vocatalk.models.Login;
import br.com.fiap.vocatalk.repositories.LoginRepository;

@Service
public class LoginService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;



    public LoginDTO registrar(LoginInjectDTO login) {

        
        login.setSenha(passwordEncoder.encode(login.getSenha()));
        log.info("LOGIN> " + passwordEncoder.encode(login.getSenha()));
        login.setUltimoLogin(LocalDateTime.now());
        login.getCliente().setDataCadastro(LocalDateTime.now());
        Login loginRetorno = loginRepository.save(new Login(login));


        return new LoginDTO(loginRetorno);
    }

    public Object login(Credencial credencial) {
        authenticationManager.authenticate(credencial.toAuthentication());

        var token = tokenService.generateToken(credencial);
        return token;
    }


}
