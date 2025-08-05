package com.br.alura.forumhub.repository;

import com.br.alura.forumhub.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);
}
