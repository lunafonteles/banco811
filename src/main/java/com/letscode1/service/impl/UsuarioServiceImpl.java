package com.letscode1.service.impl;

import com.letscode1.dto.UsuarioRequest;
import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.TipoConta;
import com.letscode1.model.Usuario;
import com.letscode1.projection.ContaView;
import com.letscode1.projection.UsuarioView;
import com.letscode1.repository.UsuarioRepository;
import com.letscode1.service.UsuarioService;
import com.letscode1.specification.UsuarioSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Usuario> getAll(String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.DESC,
                "nome"
        );

        if (nome != null) {
            return usuarioRepository.findByNome(nome, pageRequest);
        } else {
            return usuarioRepository.findAll(pageRequest);
        }
    }
    @Override
    public List<Usuario> search(String search) {
        UsuarioSpecificationBuilder builder = new UsuarioSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Usuario> spec = builder.build();
        return usuarioRepository.findAll(spec);
    }

    @Override
    public Page<UsuarioResponse> getAllByCpf(String cpf, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "cpf");
            return usuarioRepository.findByCpf(cpf, pageRequest);
        }

    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        var passwordEncrypted = passwordEncoder.encode(usuarioRequest.getSenha());
        Usuario usuario = new Usuario(usuarioRequest, passwordEncrypted);
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);

    }

    @Override
    public Usuario getById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    @Override
    public Usuario update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setSenha(usuarioRequest.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }

    public List<UsuarioView> getAllByNomeContaining(String nome) {
        return usuarioRepository.findAllByNomeContaining(nome);
    }
}
