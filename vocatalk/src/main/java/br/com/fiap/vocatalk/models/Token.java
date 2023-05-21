package br.com.fiap.vocatalk.models;

import io.swagger.v3.oas.annotations.media.Schema;

public record Token(
        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huM2Qzb2FAM2dtYWlsLmNvbSIsImlzcyI6IlZvY2FUYWxrIiwiZXhwIjoxNjg0NjI2MTU0fQ.HKdZcHqGqvH_wKnkjPdFj0nECy8Fsl7BmInzwAnxRFg@") String token,
        @Schema(example = "JWT") String type,
        @Schema(example = "Bearer") String prefix) {
}