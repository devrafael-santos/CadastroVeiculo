package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import com.raffasdev.cadastroVeiculos.domain.repository.VeiculoRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class VeiculoJpaRepositoryImpl implements VeiculoRepository {

    private final VeiculoJpaRepository jpaRepository;

    public VeiculoJpaRepositoryImpl(@Lazy VeiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByPlaca(String placa) {
        return jpaRepository.existsById(placa);
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        return jpaRepository.save(veiculo);
    }

}
