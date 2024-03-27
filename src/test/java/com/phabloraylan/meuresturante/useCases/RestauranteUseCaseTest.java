package com.phabloraylan.meuresturante.useCases;

import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteUseCase restauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");
        restaurante.setEndereco("Endereço Teste");
        restaurante.setTipoDeCozinha("Tipo Cozinha Teste");
        restaurante.setMesas(10);

        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);

        Restaurante resultado = restauranteUseCase.cadastrarRestaurante(
                "Restaurante Teste",
                "Endereço Teste",
                "Tipo Cozinha Teste",
                10);

        assertNotNull(resultado);
        assertEquals("Restaurante Teste", resultado.getNome());
        verify(restauranteRepository).save(any(Restaurante.class));
    }

    @Test
    void testListarRestaurantes() {
        Restaurante restaurante1 = new Restaurante();
        Restaurante restaurante2 = new Restaurante();
        List<Restaurante> restaurantes = Arrays.asList(restaurante1, restaurante2);

        when(restauranteRepository.findAll()).thenReturn(restaurantes);

        List<Restaurante> resultado = restauranteUseCase.listarRestaurantes();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(restauranteRepository).findAll();
    }

    @Test
    void testBuscarRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Teste Busca");
        List<Restaurante> restaurantes = Arrays.asList(restaurante);

        when(restauranteRepository.findByNomeEnderecoOuTipoCozinha("Teste Busca")).thenReturn(restaurantes);

        List<Restaurante> resultado = restauranteUseCase.buscarRestaurante("Teste Busca");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Teste Busca", resultado.get(0).getNome());
        verify(restauranteRepository).findByNomeEnderecoOuTipoCozinha("Teste Busca");
    }
}

