package com.br.alura.forumhub.repository;

import com.br.alura.forumhub.models.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository <Topic,Long> {
}
