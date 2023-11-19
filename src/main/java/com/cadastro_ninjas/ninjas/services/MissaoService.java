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
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    NinjasRepository ninjasRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> listaMissoes() {
        return missaoRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Optional<MissaoModel> buscaMissao(long id) {
        return missaoRepository.findById(id);
    }

    public List<MissaoModel> listaMissoesPorDificuldade(String dificuldade) {
        return missaoRepository.findByClassificacao(dificuldade.toUpperCase()).stream()
                .collect(Collectors.toList());
    }

    public List<MissaoModel> listaMissoesPorStatus(String status) {
        return switch (status.toLowerCase()) {
            case "concluida" -> missaoRepository.findByStatus(true).stream()
                    .collect(Collectors.toList());
            case "em-andamento" -> missaoRepository.findByStatus(false).stream()
                    .collect(Collectors.toList());
            default -> throw new RuntimeException("Status inválido!");
        };
    }

    public MissaoModel addMissao(MissaoModel missao) {
        Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(missao.getId_ninja());
        if (MissaoDificuldade.dificuldadeValida(missao.getClassificacao()) &&
                TipoMissao.tipoValido(missao.getTipo_missao()) &&
                ninjaOptional.isPresent()) {
            missaoRepository.save(missao);
            return missao;
        }
        throw new RuntimeException("Erro ao cadastrar missão!");
    }

    public MissaoModel updateMissao(long id, MissaoModel updatedMissao) {
        return missaoRepository.findById(id)
                .map(existingMissao -> {
                    Optional<NinjasModel> ninjaOptional = ninjasRepository.findById(updatedMissao.getId_ninja());
                    if (ninjaOptional.isPresent()) {
                        if (!MissaoDificuldade.dificuldadeValida(updatedMissao.getClassificacao()) ||
                                !TipoMissao.tipoValido(updatedMissao.getTipo_missao())) {
                            throw new RuntimeException("Dados inválidos!");
                        } else {
                            existingMissao.setId_ninja(updatedMissao.getId_ninja());
                            existingMissao.setClassificacao(updatedMissao.getClassificacao());
                            existingMissao.setTipo_missao(updatedMissao.getTipo_missao());
                            existingMissao.setStatus(updatedMissao.getStatus());

                            return missaoRepository.save(existingMissao);
                        }
                    } else {
                        throw new RuntimeException("Ninja inexistente!");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Missão inexistente!"));
    }
}
