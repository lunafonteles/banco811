package com.letscode1.model.validators;

import com.letscode1.dto.ContaRequest;
import com.letscode1.model.Usuario;
import com.letscode1.service.UsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContaValidator {

    @Autowired
    UsuarioService usuarioService;
    public List<String> validate(ContaRequest contaRequest) {
        List<String> validationErrors = new ArrayList<>();

        Usuario usuario = usuarioService.getById(contaRequest.getUsuarioId());

        if (usuario == null) return List.of("Usuario não existe");
        return validationErrors;



    }
}
