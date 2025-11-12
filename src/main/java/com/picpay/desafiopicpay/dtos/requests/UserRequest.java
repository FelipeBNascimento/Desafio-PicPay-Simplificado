package com.picpay.desafiopicpay.dtos.requests;

import com.picpay.desafiopicpay.infrastructure.enums.UserType;

import java.math.BigDecimal;

    public record UserRequest(String nome,
                              String documento,
                              String email,
                              String senha,
                              BigDecimal saldo,
                              UserType userType) {
    }


