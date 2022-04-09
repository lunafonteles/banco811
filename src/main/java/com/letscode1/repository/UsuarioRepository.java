package com.letscode1.repository;

import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.TipoConta;
import com.letscode1.model.Usuario;
import com.letscode1.projection.ContaView;
import com.letscode1.projection.UsuarioView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

    static List<UsuarioResponse> toResponse(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponse:: new).collect(Collectors.toList());
    };

    Page<Usuario> findByNome(String nome, Pageable pageable);

    @Query("select new com.letscode1.dto.UsuarioResponse(u.id, u.cpf, u.nome, u.dataCriacao, u.dataAtualizacao) " +
            "from Usuario u where u.cpf = :cpf")
    Page<UsuarioResponse> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    Optional<Usuario> findByLogin (String login);

    List<UsuarioView> findAllByNomeContaining (String nome);


    List<Usuario> findByNomeAndCpf(String nome, String cpf);
    List<Usuario> findByNomeIsNull();
    List<Usuario> findByNomeNotNull();
    List<Usuario> findByCpfStartingWith(String cpf);
    List<Usuario> findByCpfContaining(String cpf);
    List<Usuario> findByNomeLike(String pattern);
    List<Usuario> findByDataCriacaoAfterAndNome(LocalDateTime dataCriacao, String nome);
    List<Usuario> findByDataCriacao(LocalDateTime dataCiacao);
}
