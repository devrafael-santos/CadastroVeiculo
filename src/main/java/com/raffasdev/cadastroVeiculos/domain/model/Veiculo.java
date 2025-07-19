package com.raffasdev.cadastroVeiculos.domain.model;

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
    @Column(nullable = false, unique = true)
    private String placa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proprietario_cpf", nullable = false, unique = true)
    private Proprietario proprietario;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String chassi;

    @Column(nullable = false)
    private boolean licenciado;

}
