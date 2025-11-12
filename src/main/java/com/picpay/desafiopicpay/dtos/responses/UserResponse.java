package com.picpay.desafiopicpay.dtos.responses;

import com.picpay.desafiopicpay.infrastructure.enums.UserType;

import java.math.BigDecimal;

public record UserResponse(Long id,
                           String nome,
                           String documento,
                           String email,
                           BigDecimal saldo,
                           UserType userType) {
}
