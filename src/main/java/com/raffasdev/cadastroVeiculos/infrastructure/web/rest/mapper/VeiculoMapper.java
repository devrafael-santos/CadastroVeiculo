package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.VeiculoGetResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.VeiculoPostResponse;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public VeiculoPostResponse toPostResponse(Veiculo veiculo) {
        return new VeiculoPostResponse(
                veiculo.getPlaca(),
                veiculo.getProprietario().getCpf(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getChassi(),
                veiculo.isLicenciado()
        );
    }

    public VeiculoGetResponse toGetResponse(Veiculo veiculo) {
        return new VeiculoGetResponse(
                veiculo.getPlaca(),
                veiculo.getProprietario().getCpf(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getChassi(),
                veiculo.isLicenciado()
        );
    }
}
