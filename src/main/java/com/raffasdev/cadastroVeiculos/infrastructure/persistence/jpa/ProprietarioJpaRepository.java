package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProprietarioJpaRepository extends JpaRepository<Proprietario, String> {
    boolean existsByCpf(String cpf);

    Optional<Proprietario> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}
