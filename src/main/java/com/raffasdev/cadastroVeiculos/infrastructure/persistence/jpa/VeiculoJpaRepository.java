package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculoJpaRepository extends JpaRepository<Veiculo, String> {

    boolean existsByPlaca(String placa);

    Optional<Object> findByPlaca(String placa);
}
