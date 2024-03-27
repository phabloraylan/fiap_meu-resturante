package com.phabloraylan.meuresturante.repositories;

import com.phabloraylan.meuresturante.models.Comentario;
import com.phabloraylan.meuresturante.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository  extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByRestaurante(Restaurante restaurante);
}
