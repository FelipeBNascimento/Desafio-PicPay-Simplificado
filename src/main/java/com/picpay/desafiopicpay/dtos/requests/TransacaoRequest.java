package com.picpay.desafiopicpay.dtos.requests;

import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequest(BigDecimal valor,
                               Long idPagador,
                               Long idLojista) {
}

