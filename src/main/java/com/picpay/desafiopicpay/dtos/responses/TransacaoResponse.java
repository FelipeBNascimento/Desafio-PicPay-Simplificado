package com.picpay.desafiopicpay.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoResponse(Long id,
                                BigDecimal valor,
                                Long id_pagador,
                                Long id_lojista,
                                LocalDate data) {
}
