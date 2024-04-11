package com.example.ApiRemedios;

import com.example.ApiRemedios.Remedio.DTO.DadosAtualizarRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosCadastroRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosDetalhamentoRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosListagemRemedios;
import com.example.ApiRemedios.Remedio.Entities.Remedio;
import com.example.ApiRemedios.Remedio.Repository.RemediosRepository;
import com.example.ApiRemedios.Remedio.Services.ServicesRemedio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServicesRemedioTest {

    private ServicesRemedio services;
    private RemediosRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(RemediosRepository.class);
        services = new ServicesRemedio(repository);
    }

    @Test
    void cadastrar() {
        DadosCadastroRemedio dadosCadastro = mock(DadosCadastroRemedio.class);

        Remedio remedio = mock(Remedio.class);
        when(repository.save(any())).thenReturn(remedio);

        ResponseEntity response = services.cadastrar(dadosCadastro);

        assertEquals(ResponseEntity.ok(dadosCadastro), response);
    }

    @Test
    void listar() {
        Remedio remedio1 = mock(Remedio.class);
        Remedio remedio2 = mock(Remedio.class);

        // Criando a lista de remedios simulados
        List<Remedio> remedios = Arrays.asList(remedio1, remedio2);

        // Configurando o mock do repository para retornar a lista de remedios simulados
        when(repository.findAllByAtivoTrue()).thenReturn(remedios);

        // Chamando o método de serviço para listar remedios
        ResponseEntity<List<DadosListagemRemedios>> response = services.listar();

        // Verificando se a resposta é a esperada
        assertEquals(ResponseEntity.ok().body(remedios.stream().map(DadosListagemRemedios::new).toList()), response);
    }

    @Test
    void atualizar() {
        DadosAtualizarRemedio dadosAtualizar = mock(DadosAtualizarRemedio.class);
        Remedio remedio = mock(Remedio.class);

        when(repository.getReferenceById(anyLong())).thenReturn(remedio);

        ResponseEntity<DadosDetalhamentoRemedio> response = services.atualizar(dadosAtualizar);

        assertEquals(ResponseEntity.ok().body(new DadosDetalhamentoRemedio(remedio)), response);
    }

    @Test
    void reativar() {
        Long remedioId = 1L;
        Remedio remedio = mock(Remedio.class);
        Optional<Remedio> optionalRemedio = Optional.of(remedio);

        when(repository.findById(remedioId)).thenReturn(optionalRemedio);

        ResponseEntity<Void> response = services.reativar(remedioId);

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void excluir() {
        Long remedioId = 1L;

        ResponseEntity<Void> response = services.excluir(remedioId);

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void inativar() {
        Long remedioId = 1L;
        Remedio remedio = mock(Remedio.class);

        when(repository.getReferenceById(remedioId)).thenReturn(remedio);

        ResponseEntity<Void> response = services.inativar(remedioId);

        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
