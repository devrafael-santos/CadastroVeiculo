package com.raffasdev.cadastroVeiculos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proprietario")
public class Proprietario {

    @Id
    @Column(name = "proprietario_cpf", length = 14, nullable = false)
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;
}
