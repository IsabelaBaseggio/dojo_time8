package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.enums.NivelExperiencia;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjasService {

    @Autowired
    NinjasRepository ninjasRepository;

    public List<NinjasModel> listaNinjas(){
        List<NinjasModel> ninjas = ninjasRepository.findAll();
        return ninjas;
    }

    public Optional<NinjasModel> buscaNinja(long id){
        Optional<NinjasModel> ninja = ninjasRepository.findById(id);
        if(ninja.isPresent()){
            return ninja;
        }
        throw new RuntimeException("Ninja não encontrado!");
    }
    public NinjasModel addNinja(NinjasModel ninja){
        if (NivelExperiencia.nivelValido(ninja.getNivel())) {
            ninjasRepository.save(ninja);
            return ninja;
        }
        throw new RuntimeException("Erro ao cadastrar ninja!");
    }

    public NinjasModel updateNinja(NinjasModel novoNinja, long id){
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(id);

        if (ninjaOptional.isPresent()) {
            NinjasModel ninjaExistente = ninjaOptional.get();

            ninjaExistente.setNome(novoNinja.getNome());
            ninjaExistente.setVila(novoNinja.getVila());
            ninjaExistente.setStatus(novoNinja.getStatus());
            if (!NivelExperiencia.nivelValido(novoNinja.getNivel())) {
                throw new RuntimeException("Nível de experiência inválido!");
            }
            ninjaExistente.setNivel(novoNinja.getNivel());

            ninjasRepository.save(ninjaExistente);
            return ninjaExistente;
        } else {
            throw new RuntimeException("Ninja inexistente!");
        }
    }

}