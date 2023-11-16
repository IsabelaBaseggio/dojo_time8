package com.cadastro_ninjas.ninjas.models.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestNinja(@NotBlank(message = "O campo 'nome' não pode estar vazio.") String nome, @NotBlank(message = "O campo 'vila' não pode estar vazio.") String vila, @NotNull Boolean status, @NotBlank(message = "O campo 'nivel_experiencia' não pode estar vazio.") String nivel_experiencia) {
    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String vila() {
        return vila;
    }

    @Override
    public String nivel_experiencia() {
        return nivel_experiencia.toUpperCase();
    }
}
