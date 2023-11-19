package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class NinjasServiceTest {

    @Autowired
    private NinjasService ninjasService;

    @Test
    void testListaNinjas() {
        // Realize alguma ação que pode adicionar ninjas ao repositório antes de chamar o serviço
        // ...

        List<NinjasModel> ninjas = ninjasService.listaNinjas();

        assertNotNull(ninjas);
        assertFalse(ninjas.isEmpty());
    }

    @Test
    void testBuscaNinjaExistente() {
        // Adicione um ninja ao repositório antes de chamar o serviço
        // ...

        long idNinjaExistente = 1L;
        Optional<NinjasModel> ninjaOptional = ninjasService.buscaNinja(idNinjaExistente);

        assertTrue(ninjaOptional.isPresent());
    }

    @Test
    void testBuscaNinjaInexistente() {
        long idNinjaInexistente = 999L;
        Optional<NinjasModel> ninjaOptional = ninjasService.buscaNinja(idNinjaInexistente);

        assertTrue(ninjaOptional.isEmpty());
    }

    @Test
    void testEncontrarMissoesDoNinjaExistente() {
        // Adicione um ninja e suas missões ao repositório antes de chamar o serviço
        // ...

        long idNinjaExistente = 1L;
        List<MissaoModel> missoes = ninjasService.encontrarMissoesDoNinja(idNinjaExistente);

        assertNotNull(missoes);
    }

    @Test
    void testEncontrarMissoesDoNinjaInexistente() {
        long idNinjaInexistente = 999L;

        assertThrows(RuntimeException.class, () -> {
            ninjasService.encontrarMissoesDoNinja(idNinjaInexistente);
        });
    }
}
