package com.letscode1.service;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;

public interface ContaService {

    ContaResponse create(ContaRequest contaRequest, Integer usuarioId);

}
