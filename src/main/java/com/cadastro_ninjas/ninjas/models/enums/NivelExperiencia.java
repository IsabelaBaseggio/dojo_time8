package com.cadastro_ninjas.ninjas.models.enums;

public enum NivelExperiencia {
    GENIN("GENIN"), CHUUNIN("CHUUNIN"), JOUNIN("JOUNIN"), KAGE("KAGE");

    public String nivelExperiencia;

    NivelExperiencia(String nivel) {
        nivelExperiencia = nivel;
    }

//    public static NivelExperiencia fromString(String nivel) {
//        for (NivelExperiencia ne : NivelExperiencia.values()) {
//            if (ne.nivelExperiencia.equalsIgnoreCase(nivel)) {
//                return ne;
//            }
//        }
//        throw new IllegalArgumentException("Nível de experiência inválido: " + nivel);
//    }
}
