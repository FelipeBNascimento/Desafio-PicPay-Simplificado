package com.picpay.desafiopicpay.infrastructure.exceptions;

public class UsuarioNaoPodeTransferir extends RuntimeException {
    public UsuarioNaoPodeTransferir(String message) {
        super(message);
    }
}
