package com.cadastro_ninjas.ninjas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class MissaoModel implements Serializable {
    public static final int serialVersionID = 1;

    @Id
    private long id_missao;
    private long id;

    private String classificao;
    private String tipo_missao;
    private Boolean status;

    public long getId_missao() {
        return id_missao;
    }

    public void setId_missao(long id_missao) {
        this.id_missao = id_missao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassificao() {
        return classificao;
    }

    public void setClassificao(String classificao) {
        this.classificao = classificao;
    }

    public String getTipo_missao() {
        return tipo_missao;
    }

    public void setTipo_missao(String tipo_missao) {
        this.tipo_missao = tipo_missao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}