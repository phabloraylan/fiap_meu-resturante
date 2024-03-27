package com.phabloraylan.meuresturante.controllers;

import com.phabloraylan.meuresturante.dto.ComentarioDto;
import com.phabloraylan.meuresturante.models.Comentario;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.useCases.ComentarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    final ComentarioUseCase comentarioUseCase;

    public ComentarioController(ComentarioUseCase comentarioUseCase) {
        this.comentarioUseCase = comentarioUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ComentarioDto comentarioDto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(comentarioDto.restauranteId());

        try {
            Comentario savedComentario = comentarioUseCase.cadastrarComentario(
                    comentarioDto.comentario(),
                    comentarioDto.nomeCliente(),
                    restaurante
            );

            return ResponseEntity.ok().body(savedComentario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> list(
            @RequestParam Integer restauranteId
    ) {
        var restaurante = new Restaurante();
        restaurante.setId(restauranteId);
        var comentarios = comentarioUseCase.listarComentarios(
                restaurante
        );

        return ResponseEntity.ok().body(comentarios);
    }
}
