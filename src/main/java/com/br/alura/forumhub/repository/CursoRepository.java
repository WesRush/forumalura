package com.br.alura.forumhub.repository;

import com.br.alura.forumhub.models.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
