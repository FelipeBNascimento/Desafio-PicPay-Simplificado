package com.picpay.desafiopicpay.business;

import com.picpay.desafiopicpay.dtos.requests.UserRequest;
import com.picpay.desafiopicpay.dtos.Converter;
import com.picpay.desafiopicpay.dtos.responses.UserResponse;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import com.picpay.desafiopicpay.infrastructure.exceptions.IdNaoEncontrado;
import com.picpay.desafiopicpay.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository repository;
    private final Converter converter;

    public UserService(UserRepository repository, Converter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void criarUsuario(UserRequest request){

        UserEntity userEntity = converter.paraEntity(request);
        repository.save(userEntity);
    }

    public UserResponse visualizar(Long id){

        UserEntity usuarioBanco = repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id Não encontrado")
        );

        UserResponse response = converter.paraResponse(usuarioBanco);
        return response;
    }


}
