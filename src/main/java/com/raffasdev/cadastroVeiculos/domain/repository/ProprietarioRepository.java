package com.raffasdev.cadastroVeiculos.domain.repository;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProprietarioRepository {
    boolean existsByCpf(String cpf);

    Optional<Proprietario> findByCpf(String cpf);

    Page<Proprietario> findAll(Pageable pageable);

    void deleteByCpf(String cpf);

    Proprietario save(Proprietario proprietario);

}
