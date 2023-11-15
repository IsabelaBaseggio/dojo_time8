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

@RestController
@RequestMapping(value = "/dojo")
public class NinjasController {

    private final NinjasService ninjasService;

    public NinjasController(NinjasService ninjasService){
        this.ninjasService = ninjasService;
    }
    @Autowired
    NinjasRepository ninjasRepository;

    @Autowired
    MissaoRepository missaoRepository;
    @GetMapping("/ninjas")
    public ResponseEntity listaNinjas (){
        List<NinjasModel> ninjas = ninjasRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ninjas);
    }

    @GetMapping("/ninja/{id}")
    public ResponseEntity listaNinjaUnico(@PathVariable(value = "id") long id){
        NinjasModel ninja = ninjasRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ninja);
    }

    @PostMapping("/ninja/add")
    public void adicionadaNinja(@Valid @RequestBody RequestNinja requestNinja){
        NinjasModel ninja = new NinjasModel(requestNinja);
        ninjasService.addNinja(ninja);
    }

}
