package br.com.fiap.vocatalk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.vocatalk.dto.LoginDTO;
import br.com.fiap.vocatalk.dto.LoginInjectDTO;
import br.com.fiap.vocatalk.models.Credencial;
import br.com.fiap.vocatalk.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Login", description = "API para autenticação e gerenciamento de login")
public class LoginController {


    @Autowired
    LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/registrar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "despesa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos, ou cliente já existe")
    })
    @Operation(summary = "Registrar login/cliente", description = "Registrar um usuario")
    public ResponseEntity<LoginDTO> registrar(@RequestBody @Valid LoginInjectDTO login) {

        try {
            LoginDTO loginDTO = loginService.registrar(login);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @Operation(summary = "Realizar login", description = "Realizar o login com as credenciais do usuário")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        try {
            Object token = loginService.login(credencial);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
