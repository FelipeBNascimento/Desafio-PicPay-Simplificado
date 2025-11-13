package com.picpay.desafiopicpay.business;

import com.picpay.desafiopicpay.dtos.Converter;
import com.picpay.desafiopicpay.dtos.responses.TransacaoResponse;
import com.picpay.desafiopicpay.infrastructure.entity.TransacaoEntity;
import com.picpay.desafiopicpay.infrastructure.entity.UserEntity;
import com.picpay.desafiopicpay.infrastructure.enums.UserType;
import com.picpay.desafiopicpay.infrastructure.exceptions.IdNaoEncontrado;
import com.picpay.desafiopicpay.infrastructure.exceptions.SaldoInsuficiente;
import com.picpay.desafiopicpay.infrastructure.exceptions.UsuarioNaoPodeTransferir;
import com.picpay.desafiopicpay.infrastructure.repository.TransacaoRepository;
import com.picpay.desafiopicpay.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.picpay.desafiopicpay.infrastructure.enums.UserType.LOJISTA;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final Converter converter;
    private final UserRepository repositoryUsuario;
    private final AutorizarTransacaoService autorizarTransacaoService;
    private final NotificarService notificarService;


    public TransacaoService(TransacaoRepository repository, Converter converter,
                            UserRepository repositoryUsuario,
                            AutorizarTransacaoService autorizarTransacaoService, NotificarService notificarService) {
        this.repository = repository;
        this.converter = converter;
        this.repositoryUsuario = repositoryUsuario;
        this.autorizarTransacaoService = autorizarTransacaoService;
        this.notificarService = notificarService;

    }

    @Transactional
    public void fazerTransferencia(BigDecimal valor, Long idPagador, Long idRecebedor){

        UserEntity pagador = repositoryUsuario.findById(idPagador).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );

        verificarUsuario(pagador.getUserType());
        verificarSaldo(valor, pagador);

        UserEntity recebedor = repositoryUsuario.findById(idRecebedor).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );

        autorizarTransacaoService.autorizacaoTransferencia();

        recebedor.setSaldo(recebedor.getSaldo().add(valor));
        pagador.setSaldo(pagador.getSaldo().subtract(valor));

        repositoryUsuario.save(pagador);
        repositoryUsuario.save(recebedor);

        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setValor(valor);
        transacaoEntity.setPagador(pagador);
        transacaoEntity.setRecebedor(recebedor);
        transacaoEntity.setData(LocalDate.now());
        repository.save(transacaoEntity);

        notificarService.notificar(recebedor);

    }

    public void verificarSaldo(BigDecimal valor, UserEntity userEntity){

        if (valor.compareTo(userEntity.getSaldo()) > 0){

            throw new SaldoInsuficiente("Saldo insuficiente");
        }

    }

    public void verificarUsuario(UserType userType){

        if(userType == LOJISTA){
            throw new UsuarioNaoPodeTransferir("Lojista não pode fazer transferencia");
        }
    }

    public TransacaoResponse buscarTransacoes(Long id){

        TransacaoEntity transacaoEntity = repository.findById(id).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );

        return converter.paraResponse(transacaoEntity);
    }
}
