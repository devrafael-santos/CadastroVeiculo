package com.raffasdev.cadastroVeiculos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @Column(length = 7)
    private String veiculo_placa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proprietario_cpf", nullable = false, unique = true)
    private Proprietario proprietario_cpf;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(length = 17, nullable = false)
    private String chassi;

    @Column(nullable = false)
    private boolean licenciado;

}
