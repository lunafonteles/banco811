package com.letscode1.repository;

import com.letscode1.dto.ContaResponse;
import com.letscode1.model.Conta;
import com.letscode1.model.TipoConta;
import com.letscode1.model.Usuario;
import com.letscode1.projection.ContaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    List<Conta> findBySaldoLessThan(BigDecimal saldo);
    List<Conta> findBySaldoGreaterThan(BigDecimal saldo);
    List<Conta> findBySaldoBetween(BigDecimal saldoInicial, BigDecimal saldoFinal);
    List<Conta> findBySaldoIn(List<BigDecimal> saldos);
    List<Conta> findByTipoContaAndSaldoBetween(TipoConta tipoConta, BigDecimal saldoInicial, BigDecimal saldoFinal);
    List<Conta> findByUsuario_cpf (String cpf);
    Boolean existsByTipoConta(TipoConta tipoConta);

    @Query(value = "select c from Conta c where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf)" +
            "or (c.tipoConta = :tipoConta or c.saldo = :saldo)")
    List<Conta> findByTipoContaAndCpfOrTipoContaAndSaldo(
            @Param("tipoConta")TipoConta tipoConta,
            @Param("cpf") String cpf,
            @Param("saldo") BigDecimal saldo);

    @Query(value = "select * from conta c" +
            "where (c.tipo_conta = :tipoConta AND" +
            "c.data_criacao >= :dataCriacao)" +
            "OR c.saldo = :saldo ", nativeQuery = true)
    List<Conta> findByDataCriacaoAndTipoContaOrSaldo(
            @Param("dataCriacao") LocalDateTime dataCriacao,
            @Param("tipoConta") LocalDateTime tipoConta,
            @Param("saldo") BigDecimal saldo
    );

    @Query(value = "select * from conta c where (c.agencia = :agencia AND" +
            "c.tipo_conta = :tipoConta)" , nativeQuery = true)
    List<Conta> findByAgenciaAndTipoConta(
            @Param("agencia") Integer agencia,
            @Param("tipoConta") TipoConta tipoConta
    );

    List<ContaView> findAllByTipoConta (TipoConta tipoConta);

    Page<ContaResponse> findAllBySaldoGreaterThan(BigDecimal saldo, PageRequest pageRequest);
}
