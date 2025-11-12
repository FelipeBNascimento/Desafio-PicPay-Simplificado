package com.picpay.desafiopicpay.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacao")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "pagador_id", nullable = false)
    private UserEntity pagador;

    @ManyToOne
    @JoinColumn(name = "recebedor_id", nullable = false)
   private UserEntity recebedor;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    public TransacaoEntity(BigDecimal valor, UserEntity pagador,
                           UserEntity recebedor) {
        this.valor = valor;
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.data = LocalDate.now();
    }

    public TransacaoEntity() {
    }

    public Long getId(){

        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public UserEntity getPagador() {
        return pagador;
    }

    public void setPagador(UserEntity pagador) {
        this.pagador = pagador;
    }

    public UserEntity getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(UserEntity recebedor) {
        this.recebedor = recebedor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
