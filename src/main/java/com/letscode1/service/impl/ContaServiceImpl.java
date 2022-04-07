package com.letscode1.service.impl;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import com.letscode1.model.validators.ContaValidator;
import com.letscode1.projection.ContaView;
import com.letscode1.repository.ContaRepository;
import com.letscode1.service.ContaService;
import com.letscode1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
    public Page<ContaResponse> findAllBySaldoGreaterThan(BigDecimal saldo, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "tipoConta");
        return contaRepository.findAllBySaldoGreaterThan(saldo, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        var conta = contaRepository.findById(id).orElseThrow();
        contaRepository.delete(conta);
    }

    @Override
    public Conta update(ContaRequest contaRequest, Integer id) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setSaldo(contaRequest.getSaldo());
        conta.setTipoConta(contaRequest.getTipoConta());
        return contaRepository.save(conta);    }

    @Override
    public List<Conta> getAll() {
        return contaRepository.findAll();
    }

    public Conta getById(Integer id) {
        return contaRepository.findById(id).orElseThrow();
    }

    @Override
    public ContaResponse create(ContaRequest contaRequest) {
        //no contrutor ,String username
        //var usuario = usuarioRepository.findByLogin(username);
        //conta.setUsuario(usuario.get());
        //conta.setTipoConta(contaRequest.getTipoConta());
        contaValidator.validate(contaRequest);
        Conta conta = new Conta(contaRequest);
        conta.setUsuario(usuarioService.getById(contaRequest.getUsuarioId()));
        contaRepository.save(conta);
        return new ContaResponse(conta);



    }

}
