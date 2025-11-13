package com.picpay.desafiopicpay.controller;

import com.picpay.desafiopicpay.business.TransacaoService;
import com.picpay.desafiopicpay.dtos.requests.TransacaoRequest;
import com.picpay.desafiopicpay.dtos.responses.TransacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<TransacaoResponse> visualizarTransacao(@RequestParam Long id){

        return ResponseEntity.ok(transacaoService.buscarTransacoes(id));
    }
}
