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

    public List<MissaoModel> listaMissoes(){
        List<MissaoModel> missoes = missaoRepository.findAll();
        return missoes;
    }

    public Optional<MissaoModel> buscaMissao(long id){
        Optional<MissaoModel> missao = missaoRepository.findById(id);
        if(missao.isPresent()){
            return missao;
        }
        throw new RuntimeException("Missão não encontrada!");
    }

    public List<MissaoModel> listaMissoesPorDificuldade(String dificuldade){
        List<MissaoModel> missoes = missaoRepository.findByClassificacao(dificuldade.toUpperCase());
        return missoes;
    }

    public List<MissaoModel> listaMissoesPorStatus(String status){
        List<MissaoModel> missoes;
        if (status.equalsIgnoreCase("concluida")){
            return missoes = missaoRepository.findByStatus(true);
        } else if (status.equalsIgnoreCase("em-andamento")) {
            return missoes = missaoRepository.findByStatus(false);
        } else {
            throw new RuntimeException("Status inválido!");
        }
    }

    public MissaoModel addMissao(MissaoModel missao) {
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(missao.getId_ninja());
        if(MissaoDificuldade.dificuldadeValida(missao.getClassificacao()) && TipoMissao.tipoValido(missao.getTipo_missao()) && ninjaOptional.isPresent()){
            missaoRepository.save(missao);
            return missao;
        }
        throw new RuntimeException("Erro ao cadastrar missão!");
    }

    public MissaoModel updateMissao(long id, MissaoModel updatedMissao) {
        Optional<MissaoModel> existingMissao = missaoRepository.findById(id);
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(updatedMissao.getId_ninja());

        if (existingMissao.isPresent() && ninjaOptional.isPresent()) {
            MissaoModel missao = existingMissao.get();
            if(!MissaoDificuldade.dificuldadeValida(updatedMissao.getClassificacao()) || !TipoMissao.tipoValido(updatedMissao.getTipo_missao())){
                throw new RuntimeException("Dados inválidos!");
            } else {
                missao.setId_ninja(updatedMissao.getId_ninja());
                missao.setClassificacao(updatedMissao.getClassificacao());
                missao.setTipo_missao(updatedMissao.getTipo_missao());
                missao.setStatus(updatedMissao.getStatus());

                missaoRepository.save(missao);
                return missao;
            }
        } else {
            throw new RuntimeException("Missão ou ninja inexistente!");
        }
    }

}
