package com.raffasdev.cadastroVeiculos.domain.repository;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;

import java.util.Optional;

public interface VeiculoRepository {
    boolean existsByPlaca(String placa);

    Veiculo save(Veiculo veiculo);

    Optional<Veiculo> findByPlaca(String placa);
}
