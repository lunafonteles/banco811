package com.letscode1.service;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import com.letscode1.projection.ContaView;
import org.springframework.data.domain.Page;
import java.math.BigDecimal;
import java.util.List;

public interface ContaService {

    ContaResponse create(ContaRequest contaRequest);
    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);
    Page<ContaResponse> findAllBySaldoGreaterThan(BigDecimal saldo, int page, int size);
    void delete(Integer id);
    Conta update(ContaRequest contaRequest, Integer id);
    List<Conta> getAll();
    Conta getById(Integer id);
}
