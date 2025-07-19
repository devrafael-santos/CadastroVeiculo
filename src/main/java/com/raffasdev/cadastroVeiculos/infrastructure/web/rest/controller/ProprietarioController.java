package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.controller;

import com.raffasdev.cadastroVeiculos.application.service.ProprietarioService;
import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.ProprietarioPutRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPutResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper.ProprietarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    private final ProprietarioMapper proprietarioMapper;

    @PostMapping()
    public ResponseEntity<ProprietarioPostResponse> saveProprietario(@RequestBody @Validated
                                                                     ProprietarioPostRequest proprietarioPostRequest) {

        Proprietario proprietarioResponse = proprietarioService.saveProprietario(proprietarioPostRequest.getCpf(),
                proprietarioPostRequest.getNome());

        return new ResponseEntity<>(proprietarioMapper.toPostResponse(proprietarioResponse), HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProprietarioGetResponse> getProprietarioByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(proprietarioMapper.toGetResponse(proprietarioService.getProprietarioByCpf(cpf)),
                HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProprietarioGetResponse>> getProprietarios(Pageable pageable) {
        return new ResponseEntity<>(proprietarioService.getProprietarios(pageable).map(proprietarioMapper::toGetResponse),
                HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ProprietarioPutResponse> updateProprietario(@RequestBody @Validated
                                                                      ProprietarioPutRequest proprietarioPostRequest,
                                                                      @PathVariable String cpf) {
        Proprietario proprietarioResponse = proprietarioService.updateProprietario(proprietarioPostRequest.getNome(), cpf);

        return new ResponseEntity<>(proprietarioMapper.toPutResponse(proprietarioResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteProprietarioByCpf(@PathVariable String cpf) {
        proprietarioService.deleteProprietarioByCpf(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
