package com.picpay.desafiopicpay.infrastructure.exceptions;

public class SaldoInsuficiente extends RuntimeException {
    public SaldoInsuficiente(String message) {
        super(message);
    }
}
