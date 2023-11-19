package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.enums.NivelExperiencia;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjasService {

    @Autowired
    NinjasRepository ninjasRepository;

    @Autowired
    MissaoRepository missaoRepository;

    public List<NinjasModel> listaNinjas() {
        return ninjasRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Optional<NinjasModel> buscaNinja(long id) {
        return ninjasRepository.findById(id);
    }

    public List<MissaoModel> encontrarMissoesDoNinja(long idNinja) {
        return ninjasRepository.findById(idNinja)
                .map(ninja -> missaoRepository.findAllByNinjaId(ninja.getId()))
                .orElseThrow(() -> new RuntimeException("Ninja inexistente!"));
    }


    public NinjasModel addNinja(NinjasModel ninja) {
        if (NivelExperiencia.nivelValido(ninja.getNivel())) {
            ninjasRepository.save(ninja);
            return ninja;
        }
        throw new RuntimeException("Erro ao cadastrar ninja!");
    }

    public NinjasModel updateNinja(NinjasModel novoNinja, long id) {
        return ninjasRepository.findById(id)
                .map(ninjaExistente -> {
                    ninjaExistente.setNome(novoNinja.getNome());
                    ninjaExistente.setVila(novoNinja.getVila());
                    ninjaExistente.setStatus(novoNinja.getStatus());
                    if (!NivelExperiencia.nivelValido(novoNinja.getNivel())) {
                        throw new RuntimeException("Nível de experiência inválido!");
                    }
                    ninjaExistente.setNivel(novoNinja.getNivel());

                    return ninjasRepository.save(ninjaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Ninja inexistente!"));
    }
}
