package com.picpay.desafiopicpay.dtos.requests;

import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequest(BigDecimal valor,
                               TransacaoEntity pagador,
                               TransacaoEntity lojista,
                               LocalDate data) {
}

