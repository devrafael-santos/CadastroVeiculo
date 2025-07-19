package com.raffasdev.cadastroVeiculos.domain.repository;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;

public interface VeiculoRepository {
    boolean existsByPlaca(String placa);

    Veiculo save(Veiculo veiculo);
}
