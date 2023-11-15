package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.enums.NivelExperiencia;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean updateNinja(NinjasModel novoNinja, long id){
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(id);

        if (ninjaOptional.isPresent()) {
            NinjasModel ninjaExistente = ninjaOptional.get();

            ninjaExistente.setNome(novoNinja.getNome());
            ninjaExistente.setVila(novoNinja.getVila());
            ninjaExistente.setStatus(novoNinja.getStatus());
            ninjaExistente.setNivel(novoNinja.getNivel());

            ninjasRepository.save(ninjaExistente);
            return true;
        } else {
            return false;
        }
    }

}