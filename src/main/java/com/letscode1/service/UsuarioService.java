package com.letscode1.service;

import com.letscode1.dto.UsuarioRequest;
import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    Page<Usuario> getAll(String nome, int page, int size);
    List<Usuario> search(String search);
    Page<UsuarioResponse> getAllByCpf(String cpf, int page, int size);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    Usuario getById(Integer id);
    Usuario update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);

}
