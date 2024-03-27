package com.phabloraylan.meuresturante.useCases;

import com.phabloraylan.meuresturante.models.Reserva;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.ReservaRepository;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import com.phabloraylan.meuresturante.useCases.ReservaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaUseCaseTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private ReservaUseCase reservaUseCase;

    private Restaurante restaurante;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurante = new Restaurante();
        restaurante.setId(1);

        reserva = new Reserva();
        reserva.setNome("Cliente Teste");
        reserva.setData(LocalDate.now());
        reserva.setHora(LocalTime.of(19, 0));
        reserva.setMesa(1);
        reserva.setRestaurante(restaurante);
    }

    @Test
    void testCadastrarReservaSucesso() throws Exception {
        when(restauranteRepository.existsById(anyInt())).thenReturn(true);
        when(reservaRepository.findByRestaurante(any(Restaurante.class))).thenReturn(Collections.emptyList());
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva novaReserva = reservaUseCase.cadastrarReserva(
                "Cliente Teste",
                LocalDate.now(),
                LocalTime.of(19, 0),
                1,
                restaurante);

        assertNotNull(novaReserva);
        verify(restauranteRepository).existsById(anyInt());
        verify(reservaRepository).findByRestaurante(any(Restaurante.class));
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void testCadastrarReservaRestauranteNaoEncontrado() {
        when(restauranteRepository.existsById(anyInt())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            reservaUseCase.cadastrarReserva(
                    "Cliente Teste",
                    LocalDate.now(),
                    LocalTime.of(19, 0),
                    1,
                    restaurante);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void testCadastrarReservaMesaJaReservada() {
        when(restauranteRepository.existsById(anyInt())).thenReturn(true);
        when(reservaRepository.findByRestaurante(any(Restaurante.class))).thenReturn(Arrays.asList(reserva));

        Exception exception = assertThrows(Exception.class, () -> {
            reservaUseCase.cadastrarReserva(
                    "Cliente Teste",
                    LocalDate.now(),
                    LocalTime.of(19, 0),
                    1,
                    restaurante);
        });

        assertEquals("Mesa já reservada", exception.getMessage());
    }

    @Test
    void testListarReservas() {
        when(reservaRepository.findByRestaurante(any(Restaurante.class))).thenReturn(Arrays.asList(reserva));

        List<Reserva> reservas = reservaUseCase.listarReservas(restaurante);

        assertNotNull(reservas);
        assertFalse(reservas.isEmpty());
        assertEquals(1, reservas.size());
        verify(reservaRepository).findByRestaurante(any(Restaurante.class));
    }
}
