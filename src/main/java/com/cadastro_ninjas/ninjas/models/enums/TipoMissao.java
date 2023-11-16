package com.cadastro_ninjas.ninjas.models.enums;

public enum TipoMissao {

    RESGATE("RESGATE"), ESPIONAGEM("ESPIONAGEM"), BATALHA("BATALHA");

    public String tipoMissao;

    TipoMissao(String tipo) {
        tipoMissao = tipo;
    }

    public static boolean tipoValido(String tipo) {
        for (TipoMissao tm : TipoMissao.values()) {
            if (tm.tipoMissao.equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        throw new IllegalArgumentException("Tipo de missão inválida: " + tipo);
    }
}
