package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoJpaRepository extends JpaRepository<Veiculo, String> {
}
