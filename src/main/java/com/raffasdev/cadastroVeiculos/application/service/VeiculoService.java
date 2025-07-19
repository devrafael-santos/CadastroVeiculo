package com.raffasdev.cadastroVeiculos.application.service;

import com.raffasdev.cadastroVeiculos.application.gateways.VeiculoDataGateway;
import com.raffasdev.cadastroVeiculos.domain.exception.CPFNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.exception.PlacaAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import com.raffasdev.cadastroVeiculos.domain.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.domain.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final ProprietarioRepository proprietarioRepository;

    public final VeiculoDataGateway veiculoDataGateway;

    @Transactional
    public Veiculo saveVeiculo(String placa, String cpf) {

        if (veiculoRepository.existsByPlaca(placa)) {
            throw new PlacaAlreadyExistsException(placa);
        }

        var proprietario = proprietarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new CPFNotFoundException(cpf));

        var veiculoInfosExternas = veiculoDataGateway.getVeiculoInfosByPlaca(placa);

        Veiculo novoVeiculo = new Veiculo(
                placa,
                proprietario,
                veiculoInfosExternas.marca(),
                veiculoInfosExternas.modelo(),
                veiculoInfosExternas.chassi(),
                veiculoInfosExternas.licenciado()
        );

        return veiculoRepository.save(novoVeiculo);
    }
}
