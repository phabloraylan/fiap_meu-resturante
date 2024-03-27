package com.phabloraylan.meuresturante.useCases;
import com.phabloraylan.meuresturante.models.Comentario;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.ComentarioRepository;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import com.phabloraylan.meuresturante.useCases.ComentarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ComentarioUseCaseTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private ComentarioUseCase comentarioUseCase;

    private Restaurante restaurante;
    private Comentario comentario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurante = new Restaurante();
        restaurante.setId(1);

        comentario = new Comentario();
        comentario.setComentario("Ótimo serviço");
        comentario.setNome("Cliente Satisfeito");
        comentario.setRestaurante(restaurante);
    }

    @Test
    void testCadastrarComentarioSucesso() throws Exception {
        when(restauranteRepository.existsById(anyInt())).thenReturn(true);
        when(comentarioRepository.save(any(Comentario.class))).thenReturn(comentario);

        Comentario novoComentario = comentarioUseCase.cadastrarComentario(
                "Ótimo serviço",
                "Cliente Satisfeito",
                restaurante);

        assertNotNull(novoComentario);
        assertEquals("Ótimo serviço", novoComentario.getComentario());
        verify(restauranteRepository).existsById(anyInt());
        verify(comentarioRepository).save(any(Comentario.class));
    }

    @Test
    void testCadastrarComentarioRestauranteNaoEncontrado() {
        when(restauranteRepository.existsById(anyInt())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            comentarioUseCase.cadastrarComentario(
                    "Ótimo serviço",
                    "Cliente Satisfeito",
                    restaurante);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void testListarComentarios() {
        when(comentarioRepository.findByRestaurante(any(Restaurante.class))).thenReturn(Arrays.asList(comentario));

        List<Comentario> comentarios = comentarioUseCase.listarComentarios(restaurante);

        assertNotNull(comentarios);
        assertFalse(comentarios.isEmpty());
        assertEquals(1, comentarios.size());
        verify(comentarioRepository).findByRestaurante(any(Restaurante.class));
    }
}