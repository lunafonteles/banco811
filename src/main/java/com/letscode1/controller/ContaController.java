package com.letscode1.controller;

import com.letscode1.dto.ContaRequest;
import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import com.letscode1.projection.ContaView;
import com.letscode1.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping
    public ContaResponse create(@RequestBody ContaRequest contaRequest) {
/*        var username = RequestContextHolder.getRequestAttributes()
          .getAttribute(USERNAME, RequestAttributes.SCOPE_REQUEST)
          .toString();
        return contaService.create(contaRequest, username);*/
        return contaService.create(contaRequest);
    }

    @PutMapping
    public Conta update (@PathVariable Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.update(contaRequest, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        contaService.delete(id);
    }

    @GetMapping
    public List<Conta> getAll() {
        return contaService.getAll();
    }

    @GetMapping("/{id}")
    public Conta getById(@PathVariable Integer id) {
        return contaService.getById(id);
    }

    @GetMapping("/view")
    public List<ContaView> getAllContaViewByTipoConta(
            @RequestParam TipoConta tipoConta) {
        return contaService.getAllViewByTipoConta(tipoConta);
    }

    @GetMapping("/saldo")
    public Page<ContaResponse> findAllBySaldoGreaterThan(
            @RequestParam BigDecimal saldo,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size
   ) {
        return contaService.findAllBySaldoGreaterThan(saldo, page, size);
    }
}
