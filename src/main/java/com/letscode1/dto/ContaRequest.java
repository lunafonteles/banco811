package com.letscode1.dto;

import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ContaRequest {
    private Integer usuarioId;
    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;

    public ContaRequest(Conta conta) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
        this.usuarioId = conta.getUsuario().getId();
    }
}
