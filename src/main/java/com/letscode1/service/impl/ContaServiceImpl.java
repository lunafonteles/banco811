package com.letscode1.service.impl;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import com.letscode1.model.Usuario;
import com.letscode1.model.validators.ContaValidator;
import com.letscode1.projection.ContaView;
import com.letscode1.repository.ContaRepository;
import com.letscode1.service.ContaService;
import com.letscode1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ContaValidator contaValidator;

    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public ContaResponse create(ContaRequest contaRequest) {
        contaValidator.validate(contaRequest);
        Conta conta = new Conta(contaRequest);
        conta.setUsuario(usuarioService.getById(contaRequest.getUsuarioId()));
        contaRepository.save(conta);

        return new ContaResponse(conta);
    }

}
