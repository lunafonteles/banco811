package com.letscode1.controller;

import com.letscode1.dto.UsuarioRequest;
import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.Usuario;
import com.letscode1.projection.UsuarioView;
import com.letscode1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size
            ) {
        return usuarioService.getAll(nome, page, size);
    }

    @GetMapping("/cpf")
    public Page<UsuarioResponse> getAllbyCpf(
        @RequestParam String cpf,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "3") int size
    ) {
        return usuarioService.getAllByCpf(cpf, page, size);
    }

    @GetMapping("/search")
    public List<Usuario> search(@RequestParam String search) {
        return usuarioService.search(search);
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id) {
        return usuarioService.getById(id);
    }


    @GetMapping("/view")
    public List<UsuarioView> getAllByNomeContaining(
            @RequestParam String nome) {
        return usuarioService.getAllByNomeContaining(nome);
    }
    @PutMapping
    public Usuario update (@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.update(usuarioRequest, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
