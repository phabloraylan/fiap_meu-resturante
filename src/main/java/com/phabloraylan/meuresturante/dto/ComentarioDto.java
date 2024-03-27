package com.phabloraylan.meuresturante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioDto(
        @NotBlank(message = "Comentário é obrigatório")
        String comentario,

        @NotBlank(message = "Nome do cliente é obrigatório")
        String nomeCliente,

        @NotNull(message = "Id do restaurante é obrigatório")
        Integer restauranteId
) {
}
