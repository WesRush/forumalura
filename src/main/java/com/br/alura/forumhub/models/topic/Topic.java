package com.br.alura.forumhub.models.topic;

import com.br.alura.forumhub.models.autor.Autor;
import com.br.alura.forumhub.models.curso.Curso;
import com.br.alura.forumhub.models.resposta.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "criacao", nullable = false)
    private LocalDateTime criacao;

    private String status;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas = new ArrayList<>();

    public Topic(CadastroTopic dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.criacao = dados.criacao();
        this.status = dados.status();
        this.autor = dados.autor();
        this.curso = dados.curso();
        this.respostas = new ArrayList<>();

    }

    public void atualizarDados(RecordTopic dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.criacao() != null) {
            this.criacao = dados.criacao();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }

    }
}
