package com.br.alura.forumhub.models.topic;

import com.br.alura.forumhub.models.autor.Autor;
import com.br.alura.forumhub.models.curso.Curso;

import java.time.LocalDateTime;

public record RecordTopic(Long id, String titulo, String mensagem, LocalDateTime criacao, String status, Autor autor, Curso curso) {
    public RecordTopic(Topic topic){
       this(topic.getId(), topic.getTitulo(), topic.getMensagem(), topic.getCriacao(), topic.getStatus(), topic.getAutor(), topic.getCurso());
    }
}
