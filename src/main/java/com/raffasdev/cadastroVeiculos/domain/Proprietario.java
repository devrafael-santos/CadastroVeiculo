package com.raffasdev.cadastroVeiculos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proprietario")
public class Proprietario {

    @Id
    @Column(name = "proprietario_cpf")
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;
}
