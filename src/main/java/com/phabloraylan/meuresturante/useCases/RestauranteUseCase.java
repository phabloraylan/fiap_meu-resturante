package com.phabloraylan.meuresturante.useCases;

import com.phabloraylan.meuresturante.models.Restaurante;
import com.phabloraylan.meuresturante.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteUseCase {

    final RestauranteRepository restauranteRepository;

    public RestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante cadastrarRestaurante(
            String nome,
            String endereco,
            String tipoDeCozinha,
            Integer mesas
    ) {

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(nome);
        restaurante.setEndereco(endereco);
        restaurante.setTipoDeCozinha(tipoDeCozinha);
        restaurante.setMesas(mesas);

        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public List<Restaurante> buscarRestaurante(
            String busca
    ) {
        return restauranteRepository.findByNomeEnderecoOuTipoCozinha(busca);
    }
}
