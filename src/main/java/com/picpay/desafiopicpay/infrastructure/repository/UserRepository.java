package com.picpay.desafiopicpay.infrastructure.repository;

import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
