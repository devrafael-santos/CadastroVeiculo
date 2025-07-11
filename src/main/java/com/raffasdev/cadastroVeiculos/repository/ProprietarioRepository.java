package com.raffasdev.cadastroVeiculos.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, UUID> {
}
