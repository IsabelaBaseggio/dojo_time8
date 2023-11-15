package com.cadastro_ninjas.ninjas.models.classes;

import com.cadastro_ninjas.ninjas.models.records.RequestNinja;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="ninjas")
public class NinjasModel implements Serializable {
    public static final int serialVersionID = 1;

    @Id
    private long id;

    private String nome;
    private String vila;
    private Boolean status;
    private String nivel_experiencia;

    public NinjasModel(RequestNinja requestNinja){
        this.nome = requestNinja.nome();
        this.vila = requestNinja.vila();
        this.status = requestNinja.status();
        this.nivel_experiencia = requestNinja.nivel_experiencia();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVila() {
        return vila;
    }

    public void setVila(String vila) {
        this.vila = vila;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNivel() {
        return nivel_experiencia;
    }

    public void setNivel(String nivel) {
        this.nivel_experiencia = nivel;
    }
}
