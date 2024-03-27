package com.phabloraylan.meuresturante.useCases;

import com.phabloraylan.meuresturante.models.Comentario;
import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.ComentarioRepository;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioUseCase {

    final ComentarioRepository comentarioRepository;
    final RestauranteRepository restauranteRepository;

    public ComentarioUseCase(ComentarioRepository comentarioRepository, RestauranteRepository restauranteRepository) {
        this.comentarioRepository = comentarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Comentario cadastrarComentario(
            String comentarioTexto,
            String nome,
            Restaurante restaurante
    ) throws Exception {

        if (!restauranteRepository.existsById(restaurante.getId())) {
            throw new Exception("Restaurante não encontrado");
        }

        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioTexto);
        comentario.setNome(nome);
        comentario.setRestaurante(restaurante);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarComentarios(
            Restaurante restaurante
    ) {
        return comentarioRepository.findByRestaurante(restaurante);
    }
}
