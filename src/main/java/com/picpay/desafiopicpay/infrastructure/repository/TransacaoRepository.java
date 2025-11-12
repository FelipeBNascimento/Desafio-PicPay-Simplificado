package com.picpay.desafiopicpay.infrastructure.repository;

import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
