package com.picpay.desafiopicpay.business;

import com.picpay.desafiopicpay.dtos.Converter;
import com.picpay.desafiopicpay.dtos.requests.TransacaoRequest;
import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import com.picpay.desafiopicpay.infrastructure.exceptions.IdNaoEncontrado;
import com.picpay.desafiopicpay.infrastructure.repository.TransacaoRepository;
import com.picpay.desafiopicpay.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final Converter converter;
    private final UserRepository repositoryUsuario;

    public TransacaoService(TransacaoRepository repository, Converter converter,
                            UserRepository repositoryUsuario) {
        this.repository = repository;
        this.converter = converter;
        this.repositoryUsuario = repositoryUsuario;
    }

    @Transactional
    public void fazerTransferencia(BigDecimal valor, Long idPagador, Long idRecebedor){

        UserEntity pagador = repositoryUsuario.findById(idPagador).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );
        UserEntity logista = repositoryUsuario.findById(idRecebedor).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );

        logista.setSaldo(logista.getSaldo().add(valor));
        pagador.setSaldo(pagador.getSaldo().subtract(valor));

        repositoryUsuario.save(pagador);
        repositoryUsuario.save(logista);

        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setValor(valor);
        transacaoEntity.setPagador(pagador);
        transacaoEntity.setRecebedor(logista);
        transacaoEntity.setData(LocalDate.now());
        repository.save(transacaoEntity);


    }
}
