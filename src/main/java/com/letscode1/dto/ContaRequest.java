package com.letscode1.dto;

import com.letscode1.model.TipoConta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaRequest {
    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
}
