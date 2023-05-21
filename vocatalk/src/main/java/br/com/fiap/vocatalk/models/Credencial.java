package br.com.fiap.vocatalk.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.swagger.v3.oas.annotations.media.Schema;

public record Credencial(@Schema(example = "luan.reis@rm.com") String email,
        @Schema(example = "123BatatinhaFrita") String senha) {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}