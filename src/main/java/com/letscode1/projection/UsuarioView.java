package com.letscode1.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UsuarioView {

    @Value("#{target.nome}")
    String getNome();
}
