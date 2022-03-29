package com.letscode1.repository;

import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    static List<UsuarioResponse> toResponse(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponse:: new).collect(Collectors.toList());
    };

    List<Usuario> findByNome(String nome);
//    List<Usuario> findByNomeAndCpf(String nome, String cpf);
//    List<Usuario> findByNomeIsNull(String nome);
//    List<Usuario> findByNomeIsNotNull(String nome);
//    List<Usuario> findByCpfStartingWith(String cpf);
//    List<Usuario> findByCpfContaining(String cpf);
//    List<Usuario> findByNomeLike(String pattern);
//    List<Usuario> findByDataCriacaoAfterAndNome(LocalDateTime dataCriacao, String nome);
//    List<Usuario> findByDataCriacao(LocalDateTime dataCiacao);
}
