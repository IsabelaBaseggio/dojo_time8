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
        List<MissaoModel> missoes = missaoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(missoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity listaMissaoUnica(@PathVariable long id) {
        Optional<MissaoModel> missao = missaoRepository.findById(id);
        if (missao.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(missao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @PostMapping("/add")
    public ResponseEntity adicionaMissao(@Valid @RequestBody RequestMissao requestMissao) {
        MissaoModel missao = new MissaoModel(requestMissao);
        boolean adicionouMissao = missaoService.addMissao(missao);
        if (adicionouMissao){
            return ResponseEntity.status(HttpStatus.CREATED).body("Missão adicionada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro ao adicionar missão.");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MissaoModel> updateMissao(@PathVariable long id, @RequestBody MissaoModel updatedMissao) {
//        MissaoModel missao = missaoService.updateMissao(id, updatedMissao);
//        return missao != null ? new ResponseEntity<>(missao, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMissao(@PathVariable long id) {
//        missaoService.deleteMissao(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
