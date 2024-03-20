package com.example.ApiRemedios.Remedio.DTO;

import com.example.ApiRemedios.Remedio.Entities.Laboratorio;
import com.example.ApiRemedios.Remedio.Entities.Remedio;
import com.example.ApiRemedios.Remedio.Entities.Via;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(
        Long id,
        String nome,
        Via via,
        String lote,
        int quantidade,
        LocalDate validade,
        Laboratorio laboratorio,
        boolean ativo) {
    public DadosDetalhamentoRemedio(Remedio remedio) {
        this(
                remedio.getId(),
                remedio.getNome(),
                remedio.getVia(),
                remedio.getLote(),
                remedio.getQuantidade(),
                remedio.getValidade(),
                remedio.getLaboratorio(),
                remedio.isAtivo());
    }
}
