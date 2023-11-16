package com.cadastro_ninjas.ninjas.controllers;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.records.RequestNinja;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import com.cadastro_ninjas.ninjas.services.NinjasService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/dojo/ninja")
public class NinjasController {

    private final NinjasService ninjasService;

    public NinjasController(NinjasService ninjasService){
        this.ninjasService = ninjasService;
    }
    @Autowired
    NinjasRepository ninjasRepository;

    @Autowired
    MissaoRepository missaoRepository;

    // falta testar
    @GetMapping
    public ResponseEntity listaNinjas (){
        List<NinjasModel> ninjas = ninjasService.listaNinjas();
        return ResponseEntity.status(HttpStatus.OK).body(ninjas);
    }

    // falta testar
    @GetMapping("/{id}")
    public ResponseEntity listaNinjaUnico(@PathVariable(value = "id") long id){
        try {
            Optional<NinjasModel> ninja = ninjasService.buscaNinja(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(ninja);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
        }
    }

    @PostMapping("/add")
    public ResponseEntity adicionadaNinja(@Valid @RequestBody RequestNinja requestNinja){
        NinjasModel ninja = new NinjasModel(requestNinja);
        boolean adicionouNinja = ninjasService.addNinja(ninja);
        if(adicionouNinja){
            return ResponseEntity.status(HttpStatus.CREATED).body("Ninja adicionado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro ao adicionar ninja.");
    }

    @PutMapping("/{id}/update")
    public ResponseEntity atualizaNinja(@PathVariable(value = "id")long id, @Valid @RequestBody RequestNinja requestNinja){
        NinjasModel ninja = new NinjasModel(requestNinja);
        boolean atualizouNinja = ninjasService.updateNinja(ninja, id);
        if(atualizouNinja){
            return ResponseEntity.status(HttpStatus.CREATED).body("Ninja atualizado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro ao atualizar ninja.");
    }

}
