package com.example.ApiRemedios.Remedio.DTO;

import com.example.ApiRemedios.Remedio.Entities.Laboratorio;
import com.example.ApiRemedios.Remedio.Entities.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
        @NotNull
        Long id,

        String nome,
        Via via,
        Laboratorio Laboratorio
) {
}
