package com.raffasdev.cadastroVeiculos.repository;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, String> {
    boolean existsByCpf(String cpf);
    Proprietario findByCpf(String cpf);
}
