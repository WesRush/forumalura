package com.br.alura.forumhub.models.topic;

import com.br.alura.forumhub.models.autor.Autor;
import com.br.alura.forumhub.models.curso.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroTopic (
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,

        LocalDateTime criacao,
        @NotBlank
        String status,
        @NotNull
        @Valid
        Autor autor,
        @NotNull
        @Valid
        Curso curso

){

}
