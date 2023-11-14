package com.cadastro_ninjas.ninjas.controllers;

import com.cadastro_ninjas.ninjas.models.NinjasModel;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class CadastroController {
    @Autowired
    NinjasRepository ninjasRepository;

    @Autowired
    MissaoRepository missaoRepository;
    @GetMapping("/ninjas")
    public List<NinjasModel> listaNinjas (){
        return ninjasRepository.findAll();
    }

    @GetMapping("/ninja/{id}")
    public NinjasModel listaNinjaUnico(@PathVariable(value = "id") long id){
        return ninjasRepository.findById(id);
    }



}
