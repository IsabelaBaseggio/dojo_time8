package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import com.cadastro_ninjas.ninjas.services.NinjasService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class NinjasServiceTest {

    @InjectMocks
    private NinjasService ninjasService;

    @Mock
    private NinjasRepository ninjasRepository;


    @Test
    public void testAddNinja() {
        NinjasModel ninja = new NinjasModel("Naruto", "Folha", true, "Genin");

        when(ninjasRepository.save(Mockito.any(NinjasModel.class))).thenReturn(ninja);

        boolean result = ninjasService.addNinja(ninja);

        assertTrue(result);
    }
}
