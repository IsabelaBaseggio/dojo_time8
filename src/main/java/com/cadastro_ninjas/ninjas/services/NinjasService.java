package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.enums.NivelExperiencia;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NinjasService {

    @Autowired
    NinjasRepository ninjasRepository;
    public boolean addNinja(NinjasModel ninja){
        if (NivelExperiencia.nivelValido(ninja.getNivel())) {
            ninjasRepository.save(ninja);
            return true;
        }
        return false;
    }
}