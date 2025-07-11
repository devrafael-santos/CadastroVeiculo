package com.raffasdev.cadastroVeiculos.repository;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, String> {
}
