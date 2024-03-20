package com.example.ApiRemedios.Remedio.DTO;

import com.example.ApiRemedios.Remedio.Entities.Laboratorio;
import com.example.ApiRemedios.Remedio.Entities.Remedio;
import com.example.ApiRemedios.Remedio.Entities.Via;

import java.time.LocalDate;

public record DadosListagemRemedios(Long id, String nome, Via via, String lote, Laboratorio laboratorio, LocalDate validade) {

    public DadosListagemRemedios(Remedio remedio){
        this(remedio.getId(),remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
    }
}
