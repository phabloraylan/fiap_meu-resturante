package com.phabloraylan.meuresturante.controllers;


import com.phabloraylan.meuresturante.dto.RestauranteDto;
import com.phabloraylan.meuresturante.useCases.RestauranteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    final RestauranteUseCase restauranteUseCase;

    public RestauranteController(RestauranteUseCase restauranteUseCase) {
        this.restauranteUseCase = restauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RestauranteDto restauranteDto) {
        var savedRestaurante = restauranteUseCase.cadastrarRestaurante(
                restauranteDto.nome(),
                restauranteDto.endereco(),
                restauranteDto.tipoDeCozinha(),
                restauranteDto.mesas()
        );

        return ResponseEntity.ok().body(savedRestaurante);
    }

    @GetMapping
    public ResponseEntity<Object> list() {
        var restaurantes = restauranteUseCase.listarRestaurantes();
        return ResponseEntity.ok().body(restaurantes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> search(@RequestParam String busca) {
        var restaurantes = restauranteUseCase.buscarRestaurante(busca);
        return ResponseEntity.ok().body(restaurantes);
    }
}
