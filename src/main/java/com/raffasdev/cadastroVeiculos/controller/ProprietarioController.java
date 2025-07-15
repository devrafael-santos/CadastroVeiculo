package com.raffasdev.cadastroVeiculos.controller;

import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.service.ProprietarioService;
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

    @PostMapping()
    public ResponseEntity<ProprietarioPostResponse> saveProprietario(@RequestBody @Validated ProprietarioPostRequest proprietarioPostRequest) {
        return new ResponseEntity<>(proprietarioService.saveProprietario(proprietarioPostRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProprietarioGetResponse> getProprietarioByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(proprietarioService.getProprietarioByCpf(cpf), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProprietarioGetResponse>> getProprietarios(Pageable pageable) {
        return new ResponseEntity<>(proprietarioService.getProprietarios(pageable), HttpStatus.OK);
    }

}
