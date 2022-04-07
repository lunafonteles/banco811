package com.letscode1.repository;

import com.letscode1.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.domain.PageRequest;
import java.util.Arrays;

@DataJpaTest
public class UsuarioRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void validar_findAll_vazio_se_repository_branco() {
        var usuarios = usuarioRepository.findAll();
        Assertions.assertEquals(Arrays.asList(), usuarios);
    }

    @Test
    public void trazer_usuarios_cadastrados_no_findAll() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("1245");
        usuario.setCpf("04758966541");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Maria");
        usuario2.setSenha("1245");
        usuario2.setCpf("04758966541");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();
        Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
    }

    @Test
    public void trazer_usuarios_pelo_nome_com_paginacao() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("1245");
        usuario.setCpf("04758966541");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Luna");
        usuario2.setSenha("2342");
        usuario2.setCpf("04728966540");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        PageRequest pageRequest = PageRequest.of(0, 3);
        var usuarios = usuarioRepository.findByNome("Maria", pageRequest);
        Assertions.assertEquals(1, usuarios.getTotalElements());
        Assertions.assertEquals(usuario, usuarios.stream().findFirst().get());
    }


    }

