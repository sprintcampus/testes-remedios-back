package com.example.ApiRemedios.Remedio.Entities;

import com.example.ApiRemedios.Remedio.DTO.DadosAtualizarRemedio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RemedioTest {

    private Remedio remedio;

    @BeforeEach
    void setUp() {
        remedio = mock(Remedio.class);
    }

    @Test
    void atualizarInfomacao() {
        // Mock dos dados de atualização
        DadosAtualizarRemedio dadosAtualizar = mock(DadosAtualizarRemedio.class);
        when(dadosAtualizar.nome()).thenReturn("Dipirona");
        when(dadosAtualizar.Laboratorio()).thenReturn(Laboratorio.LABEXEMP);
        // Não atualizando a via neste teste

        // Atualizar as informações do medicamento
        remedio.atualizarInfomacao(dadosAtualizar);

        // Verificar se as informações foram atualizadas corretamente
        verify(remedio).atualizarInfomacao(dadosAtualizar);
    }

    @Test
    void inativar() {
        remedio.inativar();

        verify(remedio).inativar();
    }



    @Test
    void reativar() {
        remedio.inativar();
        remedio.reativar();

        verify(remedio).inativar();
        verify(remedio).reativar();
    }
}