package com.phabloraylan.meuresturante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Endereço é obrigatório")
        String endereco,

        @NotBlank(message = "Tipo de cozinha é obrigatório")
        String tipoDeCozinha,

        @NotNull(message = "Número de mesas é obrigatório")
        Integer mesas
) {
}
