package com.phabloraylan.meuresturante.controllers;


import com.phabloraylan.meuresturante.dto.ReservaDto;
import com.phabloraylan.meuresturante.models.Reserva;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.useCases.ReservaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    final ReservaUseCase reservaUseCase;

    public ReservaController(ReservaUseCase reservaUseCase) {
        this.reservaUseCase = reservaUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ReservaDto reservaDto) {

        Restaurante restaurante = new Restaurante();
        restaurante.setId(reservaDto.restauranteId());

        LocalDate data = LocalDate.parse(reservaDto.data());
        LocalTime hora = LocalTime.parse(reservaDto.hora());

        try {
            Reserva savedReserva = reservaUseCase.cadastrarReserva(
                    reservaDto.nomeCliente(),
                    data,
                    hora,
                    reservaDto.mesa(),
                    restaurante
            );

            return ResponseEntity.ok().body(savedReserva);
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
        var reservas = reservaUseCase.listarReservas(
                restaurante
        );

        return ResponseEntity.ok().body(reservas);
    }
}
