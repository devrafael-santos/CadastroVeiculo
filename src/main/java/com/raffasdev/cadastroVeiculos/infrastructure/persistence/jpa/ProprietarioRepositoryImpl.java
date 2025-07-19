package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProprietarioRepositoryImpl implements ProprietarioRepository {

    private final ProprietarioJpaRepository jpaRepository;

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public Optional<Proprietario> findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf);
    }

    @Override
    public Page<Proprietario> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public void deleteByCpf(String cpf) {
        jpaRepository.deleteByCpf(cpf);
    }

    @Override
    public Proprietario save(Proprietario proprietario) {
        return jpaRepository.save(proprietario);
    }
}
