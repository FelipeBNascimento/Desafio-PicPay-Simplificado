package com.picpay.desafiopicpay.dtos;

import com.picpay.desafiopicpay.dtos.requests.TransacaoRequest;
import com.picpay.desafiopicpay.dtos.requests.UserRequest;
import com.picpay.desafiopicpay.dtos.responses.TransacaoResponse;
import com.picpay.desafiopicpay.dtos.responses.UserResponse;
import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public UserEntity paraEntity(UserRequest request) {

        UserEntity usuario = new UserEntity();

        usuario.setNome(request.nome());
        usuario.setDocumento(request.documento());
        usuario.setEmail(request.email());
        usuario.setSaldo(request.saldo());
        usuario.setSenha(request.senha());
        usuario.setUserType(request.userType());

        return usuario;

    }

    public UserResponse paraResponse(UserEntity entity) {

        UserResponse userResponse = new UserResponse(entity.getId(),
                entity.getNome(),
                entity.getDocumento(),
                entity.getEmail(),
                entity.getSaldo(),
                entity.getUserType());

        return userResponse;
    }

    public TransacaoEntity paraTransacaoEntity(TransacaoRequest request) {

        TransacaoEntity entity = new TransacaoEntity();

        entity.setValor(request.valor());
        entity.setData(request.data());
        entity.setPagador(request.pagador().getPagador());
        entity.setRecebedor(request.lojista().getRecebedor());
        return entity;
    }

    public TransacaoResponse paraResponse(TransacaoEntity entity) {

        TransacaoResponse response = new TransacaoResponse(entity.getId(),
                entity.getValor(),
                entity.getPagador().getId(),
                entity.getRecebedor().getId(),
                entity.getData());

        return response;
    }
}
