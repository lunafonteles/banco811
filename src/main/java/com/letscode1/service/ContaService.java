package com.letscode1.service;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.TipoConta;
import com.letscode1.projection.ContaView;
import java.util.List;

public interface ContaService {

    ContaResponse create(ContaRequest contaRequest);

    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);
}
