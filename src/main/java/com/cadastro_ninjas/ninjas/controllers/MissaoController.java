package com.cadastro_ninjas.ninjas.controllers;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.services.MissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/missao")
public class MissaoController {

    private final MissaoService missaoService;

    @Autowired
    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping
    public ResponseEntity<List<MissaoModel>> getAllMissoes() {
        List<MissaoModel> missoes = missaoService.getAllMissoes();
        return new ResponseEntity<>(missoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissaoModel> getMissaoById(@PathVariable long id) {
        Optional<MissaoModel> missao = missaoService.getMissaoById(id);
        return missao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MissaoModel> addMissao(@RequestBody MissaoModel missao) {
        MissaoModel addedMissao = missaoService.addMissao(missao);
        return new ResponseEntity<>(addedMissao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissaoModel> updateMissao(@PathVariable long id, @RequestBody MissaoModel updatedMissao) {
        MissaoModel missao = missaoService.updateMissao(id, updatedMissao);
        return missao != null ? new ResponseEntity<>(missao, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMissao(@PathVariable long id) {
        missaoService.deleteMissao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
