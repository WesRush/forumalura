package com.br.alura.forumhub.controller;

import com.br.alura.forumhub.models.topic.CadastroTopic;
import com.br.alura.forumhub.models.topic.RecordTopic;
import com.br.alura.forumhub.models.topic.Topic;
import com.br.alura.forumhub.repository.TopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroTopic dados, UriComponentsBuilder builder){
        var topico = new Topic(dados);
        topicRepository.save(topico);
        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body((new RecordTopic(topico)));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTopico(@PathVariable Long id){
        var topico = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new RecordTopic(topico));

    }
    @GetMapping
    public ResponseEntity<Page<RecordTopic>> listarTopicos(@PageableDefault(sort = {"titulo"}) Pageable pageable){
        var pagina = topicRepository.findAll(pageable).map(RecordTopic::new);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopic(
            @PathVariable Long id,
            @RequestBody @Valid RecordTopic dados) {

        var topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        topic.atualizarDados(dados);
        topicRepository.save(topic);

        return ResponseEntity.ok(new RecordTopic(topic));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
        if (!topicRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
