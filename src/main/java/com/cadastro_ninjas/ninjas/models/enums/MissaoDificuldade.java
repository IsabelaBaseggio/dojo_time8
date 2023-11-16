package com.cadastro_ninjas.ninjas.models.enums;

public enum MissaoDificuldade {

    D("D"), C("C"), B("B"), A("A"), S("S");

    public String missaoDificuldade;

    MissaoDificuldade(String dificuldade) {
        missaoDificuldade = dificuldade;
    }

    public static boolean dificuldadeValida(String dificuldade) {
        for (MissaoDificuldade mf : MissaoDificuldade.values()) {
            if (mf.missaoDificuldade.equalsIgnoreCase(dificuldade)) {
                return true;
            }
        }
        throw new IllegalArgumentException("Dificuldade de missão inválida: " + dificuldade);
    }
}
