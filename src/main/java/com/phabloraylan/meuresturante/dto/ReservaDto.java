package com.phabloraylan.meuresturante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservaDto(

            @NotBlank(message = "Data é obrigatória")
            String data,

            @NotBlank(message = "Hora é obrigatória")
            String hora,

            @NotNull(message = "Número da mesa é obrigatório")
            Integer mesa,

            @NotBlank(message = "Nome do cliente é obrigatório")
            String nomeCliente,

            @NotNull(message = "Id do restaurante é obrigatório")
            Integer restauranteId
) {
}
