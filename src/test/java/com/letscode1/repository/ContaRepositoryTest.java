package com.letscode1.repository;

import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void validar_findAll_vazio_se_repository_branco() {
        var contas = contaRepository.findAll();
        Assertions.assertEquals(Arrays.asList(), contas);
    }

    @Test
    public void trazer_contas_cadastradas_no_findAll() {
        Conta conta = new Conta();
        conta.setAgencia(1);
        conta.setNumero(1245);
        conta.setSaldo(BigDecimal.valueOf(4000));
        conta.setTipoConta(TipoConta.PF);

        Conta conta2 = new Conta();
        conta2.setAgencia(2);
        conta2.setNumero(1245);
        conta2.setSaldo(BigDecimal.valueOf(4000));
        conta2.setTipoConta(TipoConta.PJ);

        entityManager.persist(conta);
        entityManager.persist(conta2);

        var contas = contaRepository.findAll();
        Assertions.assertEquals(Arrays.asList(conta, conta2), contas);
    }

}
