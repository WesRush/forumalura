package com.br.alura.forumhub.controller;

import com.br.alura.forumhub.config.security.TokenJWT;
import com.br.alura.forumhub.config.security.TokenService;
import com.br.alura.forumhub.models.Login;
import com.br.alura.forumhub.user.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid Login dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return  ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}