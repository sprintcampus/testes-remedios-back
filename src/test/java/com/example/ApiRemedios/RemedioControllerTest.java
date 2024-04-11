package com.example.ApiRemedios;

import com.example.ApiRemedios.Controllers.RemedioController;
import com.example.ApiRemedios.Remedio.DTO.*;
import com.example.ApiRemedios.Remedio.Entities.Laboratorio;
import com.example.ApiRemedios.Remedio.Entities.Via;
import com.example.ApiRemedios.Remedio.Services.ServicesRemedio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RemedioControllerTest {

    @Mock
    private ServicesRemedio remedioService;

    @InjectMocks
    private RemedioController remedioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCadastrarRemedio() {
        DadosCadastroRemedio dadosCadastroRemedio = new DadosCadastroRemedio("Paracetamol", Via.ORAL, "123ABC", 50, LocalDate.now(), Laboratorio.MEDLEY);
        ResponseEntity<Void> response = ResponseEntity.ok().build();
        when(remedioService.cadastrar(dadosCadastroRemedio)).thenReturn(response);

        ResponseEntity<Void> resultado = remedioController.cadastrar(dadosCadastroRemedio);

        assertEquals(response, resultado);
        verify(remedioService, times(1)).cadastrar(dadosCadastroRemedio);
    }

    @Test
    void testListarRemedios() {
        DadosListagemRemedios dadosRemedio = new DadosListagemRemedios(1L, "Paracetamol", Via.ORAL, "123ABC", Laboratorio.MEDLEY, LocalDate.now());
        when(remedioService.listar()).thenReturn(ResponseEntity.ok(Collections.singletonList(dadosRemedio)));

        ResponseEntity<List<DadosListagemRemedios>> resultado = remedioController.listar();

        assertEquals(Collections.singletonList(dadosRemedio), resultado.getBody());
        assertEquals(200, resultado.getStatusCodeValue());
    }

    @Test
    void testAtualizarRemedio() {
        DadosAtualizarRemedio dadosAtualizarRemedio = new DadosAtualizarRemedio(1L, "Paracetamol Novo Nome", Via.ORAL, Laboratorio.MEDLEY);
        DadosDetalhamentoRemedio detalhamentoRemedio = new DadosDetalhamentoRemedio(1L, "Paracetamol Novo Nome", Via.ORAL, "123ABC", 50, LocalDate.now(), Laboratorio.MEDLEY, true);
        when(remedioService.atualizar(dadosAtualizarRemedio)).thenReturn(ResponseEntity.ok(detalhamentoRemedio));

        ResponseEntity<DadosDetalhamentoRemedio> resultado = remedioController.atualizar(dadosAtualizarRemedio);

        assertEquals(detalhamentoRemedio, resultado.getBody());
        assertEquals(200, resultado.getStatusCodeValue());
    }

    @Test
    void testReativarRemedio() {
        Long id = 1L;
        ResponseEntity<Void> response = ResponseEntity.noContent().build();
        when(remedioService.reativar(id)).thenReturn(response);

        ResponseEntity<Void> resultado = remedioController.reativar(id);

        assertEquals(response, resultado);
        verify(remedioService, times(1)).reativar(id);
    }

    @Test
    void testExcluirRemedio() {
        Long id = 1L;
        ResponseEntity<Void> response = ResponseEntity.noContent().build();
        when(remedioService.excluir(id)).thenReturn(response);

        ResponseEntity<Void> resultado = remedioController.excluir(id);

        assertEquals(response, resultado);
        verify(remedioService, times(1)).excluir(id);
    }

    @Test
    void testInativarRemedio() {
        Long id = 1L;
        ResponseEntity<Void> response = ResponseEntity.noContent().build();
        when(remedioService.inativar(id)).thenReturn(response);

        ResponseEntity<Void> resultado = remedioController.inativar(id);

        assertEquals(response, resultado);
        verify(remedioService, times(1)).inativar(id);
    }
}
