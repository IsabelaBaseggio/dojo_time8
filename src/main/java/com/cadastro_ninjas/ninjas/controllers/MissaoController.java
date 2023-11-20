package com.cadastro_ninjas.ninjas.controllers;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.records.RequestMissao;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.services.MissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dojo/missao")
public class MissaoController {

    private final MissaoService missaoService;

    @Autowired
    MissaoRepository missaoRepository;

    @Autowired
    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping
    public ResponseEntity listaMissoes() {
        List<MissaoModel> missoes = missaoService.listaMissoes();
        return ResponseEntity.status(HttpStatus.OK).body(missoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity listaMissaoUnica(@PathVariable long id) {
        try {
            Optional<MissaoModel> missao = missaoService.buscaMissao(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(missao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/dificuldade/{dificuldade}")
    public ResponseEntity listaMissoesPorDificuldade(@PathVariable String dificuldade){
        List<MissaoModel> missoes = missaoService.listaMissoesPorDificuldade(dificuldade);
        return ResponseEntity.status(HttpStatus.OK).body(missoes);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity listaMissoesPorStatus(@PathVariable String status){
        try {
            List<MissaoModel> missoes = missaoService.listaMissoesPorStatus(status);
            return ResponseEntity.status(HttpStatus.OK).body(missoes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/ninja-rank-alto")
    public ResponseEntity listaMissoesPorNinjaRankAlto(){
        try {
            List<MissaoModel> missoes = missaoService.listaMissoesResgateNinjasRankAlto();
            return ResponseEntity.status(HttpStatus.OK).body(missoes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity adicionaMissao(@Valid @RequestBody RequestMissao requestMissao) {
        try{
            MissaoModel missao = new MissaoModel(requestMissao);
            MissaoModel missaoCadastrada = missaoService.addMissao(missao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Missão adicionada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity atualizaMissao(@PathVariable(value = "id") long id, @Valid @RequestBody RequestMissao requestMissao) {
        try {
            MissaoModel missao = new MissaoModel(requestMissao);
            MissaoModel missaoAtualizada = missaoService.updateMissao(id, missao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Missão atualizada com sucesso!");
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

}
