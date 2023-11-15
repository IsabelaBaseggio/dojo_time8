package com.cadastro_ninjas.ninjas.models.records;

import jakarta.validation.constraints.NotBlank;

public record RequestNinja(@NotBlank String nome, @NotBlank String vila, @NotBlank Boolean status, @NotBlank String nivel_experiencia) {
    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String vila() {
        return vila;
    }

    @Override
    public Boolean status() {
        return status;
    }

    @Override
    public String nivel_experiencia() {
        return nivel_experiencia;
    }
}
