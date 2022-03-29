package com.letscode1.service.impl;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.repository.ContaRepository;
import com.letscode1.service.ContaService;
import com.letscode1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public ContaResponse create(ContaRequest contaRequest, Integer usuarioId) {
        var usuario = usuarioService.getById(usuarioId);
        Conta conta = new Conta();
        conta.setUsuario(usuario);
        conta.setAgencia(contaRequest.getAgencia());
        conta.setNumero(contaRequest.getNumero());
        conta.setSaldo(contaRequest.getSaldo());
        conta.setTipoConta(contaRequest.getTipoConta());

        contaRepository.save(conta);
        return new ContaResponse(conta);
    }

}
