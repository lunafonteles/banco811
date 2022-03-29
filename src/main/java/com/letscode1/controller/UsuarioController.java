package com.letscode1.controller;

import com.letscode1.dto.UsuarioRequest;
import com.letscode1.dto.UsuarioResponse;
import com.letscode1.model.Usuario;
import com.letscode1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponse> getAll(@RequestParam(required = false) String nome) {
        return usuarioService.getAll(nome);
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id) {
        return usuarioService.getById(id);
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
