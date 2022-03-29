package com.letscode1.service;

import com.letscode1.dto.UsuarioRequest;
import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.Usuario;
import java.util.List;

public interface UsuarioService {

    List<UsuarioResponse> getAll(String nome);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    Usuario getById(Integer id);
    Usuario update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);
}
