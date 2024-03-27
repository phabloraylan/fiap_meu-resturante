package com.phabloraylan.meuresturante.useCases;

import com.phabloraylan.meuresturante.models.Reserva;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.ReservaRepository;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaUseCase {

    final ReservaRepository reservaRepository;
    final RestauranteRepository restauranteRepository;

    public ReservaUseCase(ReservaRepository reservaRepository, RestauranteRepository restauranteRepository) {
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Reserva cadastrarReserva(
            String nome,
            LocalDate data,
            LocalTime hora,
            Integer mesa,
            Restaurante restaurante
    ) throws Exception {

        // verifica se o restaurante existe
        if (!restauranteRepository.existsById(restaurante.getId())) {
            throw new Exception("Restaurante não encontrado");
        }

        // verifica se a mesa está disponível
        List<Reserva> reservas = listarReservas(restaurante);
        for (Reserva reserva : reservas) {
            if (reserva.getMesa().equals(mesa) && reserva.getData().equals(data) && reserva.getHora().equals(hora)) {
                throw new Exception("Mesa já reservada");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setNome(nome);
        reserva.setData(data);
        reserva.setHora(hora);
        reserva.setMesa(mesa);
        reserva.setRestaurante(restaurante);

        return reservaRepository.save(reserva);

    }

    public List<Reserva> listarReservas(
            Restaurante restaurante
    ) {

        return reservaRepository.findByRestaurante(restaurante);

    }
}
