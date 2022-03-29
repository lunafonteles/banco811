package com.letscode1.dto;

import com.letscode1.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UsuarioResponse {
    private Integer id;
    private String cpf;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataAtualizacao = usuario.getDataAtualizacao();
    }

    public static List<UsuarioResponse> toResponse(List<Usuario> usuarios){
        return  usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }
}
