package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.enums.MissaoDificuldade;
import com.cadastro_ninjas.ninjas.models.enums.TipoMissao;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    NinjasRepository ninjasRepository;



    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }


    public boolean addMissao(MissaoModel missao) {
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(missao.getId_ninja());

        if(MissaoDificuldade.dificuldadeValida(missao.getClassificacao()) && TipoMissao.tipoValido(missao.getTipo_missao()) && ninjaOptional.isPresent()){
            missaoRepository.save(missao);
            return true;
        }
        return false;
    }

    // ERRO AO FAZER VALIDAÇÃO DO CLASSIFICACAO, TIPO E SE ID_NINJA EXISTE
    // ESTÁ SALVANDO VALORES DIFERENTES DO SETADOS NOS ENUMS E ATUALIZANDO ASSIM NO DB
    // VERIFICAR IF DA VALIDAÇÕES DOS ENUMS E DO ID_NINJA TALVEZ ???
    public boolean updateMissao(long id, MissaoModel updatedMissao) {
        Optional<MissaoModel> existingMissao = missaoRepository.findById(id);
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(updatedMissao.getId_ninja());

        if (existingMissao.isPresent() && ninjaOptional.isPresent()) {
            MissaoModel missao = existingMissao.get();
            if(!MissaoDificuldade.dificuldadeValida(updatedMissao.getClassificacao()) || !TipoMissao.tipoValido(updatedMissao.getTipo_missao())){
                return false;
            } else {
                missao.setId_ninja(updatedMissao.getId_ninja());
                missao.setClassificacao(updatedMissao.getClassificacao());
                missao.setTipo_missao(updatedMissao.getTipo_missao());
                missao.setStatus(updatedMissao.getStatus());

                missaoRepository.save(missao);
                return true;
            }
        } else {
            return false;
        }
    }

//    public void deleteMissao(long id) {
//        missaoRepository.deleteById(id);
//    }

}
