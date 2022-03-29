package com.letscode1.model.validators;

import com.letscode1.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioValidator {
    public List<String> validate(Usuario usuario) {
        List<String> validationErrors = new ArrayList<>();

        if (usuario.getId() == null) return List.of("Usuario não existe");

        return validationErrors;

    }
}
