package com.phabloraylan.meuresturante.repositories;

import com.phabloraylan.meuresturante.models.Reserva;
import com.phabloraylan.meuresturante.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository  extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByRestaurante(Restaurante restaurante);
}
