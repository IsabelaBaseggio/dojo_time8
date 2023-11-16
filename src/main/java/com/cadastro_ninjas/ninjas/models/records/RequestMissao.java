package com.cadastro_ninjas.ninjas.models.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestMissao(@NotNull(message = "O campo 'id_ninja' não pode estar vazio.") long id_ninja, @NotBlank(message = "O campo 'classificacao' não pode estar vazio.") String classificacao, @NotBlank(message = "O campo 'tipo_missao' não pode estar vazio.") String tipo_missao, @NotNull Boolean status) {
    @Override
    public long id_ninja() {
        return id_ninja;
    }

    @Override
    public String classificacao() {
        return classificacao;
    }

    @Override
    public String tipo_missao() {
        return tipo_missao;
    }

    @Override
    public Boolean status() {
        return status;
    }
}
