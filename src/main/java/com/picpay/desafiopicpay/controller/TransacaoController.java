package com.picpay.desafiopicpay.controller;

import com.picpay.desafiopicpay.business.TransacaoService;
import com.picpay.desafiopicpay.dtos.requests.TransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> fazerTransaferencia(@RequestBody TransacaoRequest request){

        transacaoService.fazerTransferencia(request.valor(), request.idPagador(),
                request.idLojista());

        return ResponseEntity.ok().build();

    }
}
