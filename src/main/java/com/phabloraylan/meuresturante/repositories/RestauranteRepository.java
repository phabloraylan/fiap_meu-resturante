package com.phabloraylan.meuresturante.repositories;

import com.phabloraylan.meuresturante.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Integer> {

    @Query("SELECT r FROM Restaurante r WHERE lower(r.nome) LIKE lower(concat('%', ?1, '%')) OR lower(r.endereco) LIKE lower(concat('%', ?1, '%')) OR lower(r.tipoDeCozinha) LIKE lower(concat('%', ?1, '%'))")
    List<Restaurante> findByNomeEnderecoOuTipoCozinha(String parametro);
}
