package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> getAllMissoes() {
        return missaoRepository.findAll();
    }

    public Optional<MissaoModel> getMissaoById(long id) {
        return missaoRepository.findById(id);
    }

    public MissaoModel addMissao(MissaoModel missao) {
        return missaoRepository.save(missao);
    }

    public MissaoModel updateMissao(long id, MissaoModel updatedMissao) {
        Optional<MissaoModel> existingMissao = missaoRepository.findById(id);

        if (existingMissao.isPresent()) {
            MissaoModel missao = existingMissao.get();
            missao.setClassificao(updatedMissao.getClassificao());
            missao.setTipo_missao(updatedMissao.getTipo_missao());
            missao.setStatus(updatedMissao.getStatus());

            return missaoRepository.save(missao);
        } else {
            return null;
        }
    }

    public void deleteMissao(long id) {
        missaoRepository.deleteById(id);
    }

}
