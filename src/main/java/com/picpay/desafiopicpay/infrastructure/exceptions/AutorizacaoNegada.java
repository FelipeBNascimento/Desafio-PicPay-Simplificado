package com.picpay.desafiopicpay.infrastructure.exceptions;

public class AutorizacaoNegada extends RuntimeException {
    public AutorizacaoNegada(String message) {
        super(message);
    }
}
