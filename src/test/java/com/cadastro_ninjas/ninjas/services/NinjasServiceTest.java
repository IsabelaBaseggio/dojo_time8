package com.cadastro_ninjas.ninjas.services;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import com.cadastro_ninjas.ninjas.models.records.RequestMissao;
import com.cadastro_ninjas.ninjas.models.records.RequestNinja;
import com.cadastro_ninjas.ninjas.repository.MissaoRepository;
import com.cadastro_ninjas.ninjas.repository.NinjasRepository;
import com.cadastro_ninjas.ninjas.services.NinjasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.never;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class NinjasServiceTest {

    @Mock
    private NinjasRepository ninjasRepository;

    @Mock
    private MissaoRepository missaoRepository;

    @InjectMocks
    private NinjasService ninjasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListaNinjas() {
        List<NinjasModel> mockNinjasList = new ArrayList<>();
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "Genin");
        RequestNinja requestNinja2 = new RequestNinja("Tsunade", "Konoha", false, "kage");
        mockNinjasList.add(new NinjasModel(requestNinja));
        mockNinjasList.add(new NinjasModel(requestNinja2));

        when(ninjasRepository.findAll()).thenReturn(mockNinjasList);

        List<NinjasModel> resultado = ninjasService.listaNinjas();

        verify(ninjasRepository).findAll();

        assertEquals(mockNinjasList, resultado);

        assertEquals(mockNinjasList.size(), resultado.size());
    }

    @Test
    void testBuscaNinjaExistente() {
        long id = 1L;

        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "Genin");
        NinjasModel ninjaMock = new NinjasModel(requestNinja);

        when(ninjasRepository.findById(id)).thenReturn(Optional.of(ninjaMock));

        Optional<NinjasModel> ninjaResultado = ninjasService.buscaNinja(id);

        verify(ninjasRepository).findById(id);

        assertTrue(ninjaResultado.isPresent());
        assertEquals(ninjaMock, ninjaResultado.get());
    }

    @Test
    void testBuscaNinjaInexistente() {
        long id = 10L;

        when(ninjasRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            ninjasService.buscaNinja(id);
        });

        verify(ninjasRepository).findById(id);
    }

    @Test
    void testEncontrarMissoesDoNinja() {
        long id = 1L;
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "Genin");
        NinjasModel ninjaMock = new NinjasModel(requestNinja);

        when(ninjasRepository.findById(id)).thenReturn(Optional.of(ninjaMock));

        List<MissaoModel> mockMissoesList = new ArrayList<>();
        RequestMissao requestMissao1 = new RequestMissao(id, "D", "RESGATE", true);
        RequestMissao requestMissao2 = new RequestMissao(id, "B", "ESPIONAGEM", false);
        mockMissoesList.add(new MissaoModel(requestMissao1));
        mockMissoesList.add(new MissaoModel(requestMissao2));

        when(missaoRepository.findAllByNinjaId(id)).thenReturn(mockMissoesList);

        List<MissaoModel> resultadoMissoes = ninjasService.encontrarMissoesDoNinja(id);

        verify(ninjasRepository).findById(id);

        verify(missaoRepository).findAllByNinjaId(id);

        assertEquals(mockMissoesList, resultadoMissoes);

        assertEquals(mockMissoesList.size(), resultadoMissoes.size());
    }

    @Test
    void testEncontrarMissoesDoNinjaInexistente() {
        long id = 10L;

        when(ninjasRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            ninjasService.encontrarMissoesDoNinja(id);
        });

        verify(ninjasRepository).findById(id);
    }

    @Test
    public void testAddNinja() {
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "Genin");

        NinjasModel ninja = new NinjasModel(requestNinja);

        when(ninjasRepository.save(ninja)).thenReturn(ninja);

        NinjasModel resultado = ninjasService.addNinja(ninja);

        verify(ninjasRepository).save(ninja);

        assertEquals(ninja, resultado);
    }

    @Test
    void testAddNinjaNivelExperienciaInvalido() {
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "RONIN");

        NinjasModel ninja = new NinjasModel(requestNinja);

        assertThrows(IllegalArgumentException.class, () -> {
            ninjasService.addNinja(ninja);
        });
    }

    @Test
    void testUpdateNinja() {
        long id = 1L;
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "JOUNIN");

        NinjasModel ninja = new NinjasModel(requestNinja);

        when(ninjasRepository.findById(id)).thenReturn(Optional.of(ninja));

        RequestNinja requestNinjaUpdate = new RequestNinja("Sasuke", "Konoha", true, "CHUUNIN");
        NinjasModel ninjaUpdate = new NinjasModel(requestNinjaUpdate);

        NinjasModel resultadoNinjaAtualizado = ninjasService.updateNinja(ninjaUpdate, id);

        assertEquals(ninja, resultadoNinjaAtualizado);
    }

    @Test
    void testUpdateNinjaInexistente() {
        long id = 10L;

        when(ninjasRepository.findById(id)).thenReturn(Optional.empty());

        RequestNinja updatedNinjaData = new RequestNinja("Sasuke", "Konoha", true, "CHUUNIN");
        NinjasModel updatedNinja = new NinjasModel(updatedNinjaData);

        assertThrows(RuntimeException.class, () -> {
            ninjasService.updateNinja(updatedNinja, id);
        });
    }

    @Test
    void testUpdateNinjaNivelExperienciaInvalido() {
        long id = 1L;
        RequestNinja requestNinja = new RequestNinja("Naruto", "Konoha", true, "JOUNIN");

        NinjasModel ninja = new NinjasModel(requestNinja);

        when(ninjasRepository.findById(id)).thenReturn(Optional.of(ninja));

        RequestNinja requestNinjaUpdate = new RequestNinja("Sasuke", "Konoha", true, "RONIN");
        NinjasModel ninjaUpdate = new NinjasModel(requestNinjaUpdate);

        assertThrows(IllegalArgumentException.class, () -> {
            ninjasService.updateNinja(ninjaUpdate, id);
        });
    }
}
