package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.controller;

import com.raffasdev.cadastroVeiculos.application.service.VeiculoService;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.VeiculoPostRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.VeiculoGetResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.VeiculoPostResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper.VeiculoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    private final VeiculoMapper veiculoMapper;

    @PostMapping()
    public ResponseEntity<VeiculoPostResponse> saveVeiculo(
            @Validated @RequestBody VeiculoPostRequest veiculoPostRequest) {

        var veiculoSalvo = veiculoService.saveVeiculo(veiculoPostRequest.getPlaca(),
                veiculoPostRequest.getCpf());

        var veiculoResponse = veiculoMapper.toPostResponse(veiculoSalvo);

        return new ResponseEntity<>(veiculoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoGetResponse> getVeiculoByPlaca(
            @PathVariable String placa) {

        var veiculo = veiculoService.getVeiculoByPlaca(placa);

        return new ResponseEntity<>(veiculoMapper.toGetResponse(veiculo), HttpStatus.OK);
    }
}
