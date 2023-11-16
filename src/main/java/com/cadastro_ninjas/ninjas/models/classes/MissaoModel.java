package com.cadastro_ninjas.ninjas.models.classes;

import com.cadastro_ninjas.ninjas.models.records.RequestMissao;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="missoes")
public class MissaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_ninja;

    private String classificacao;
    private String tipo_missao;
    private Boolean status;

    public MissaoModel(){}

    public MissaoModel(RequestMissao requestMissao){
        this.id_ninja = requestMissao.id_ninja();
        this.classificacao = requestMissao.classificacao();
        this.tipo_missao = requestMissao.tipo_missao();
        this.status = requestMissao.status();
    }

    public long getId() {
        return id;
    }

    public long getId_ninja() {
        return id_ninja;
    }

    public void setId_ninja(long id) {
        this.id_ninja = id_ninja;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
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