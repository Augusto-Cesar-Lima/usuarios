package com.augustolima.usuarios.infrastructure.repository;



import com.augustolima.usuarios.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    // Optinal é usado para evitar q a aplicacao quebre, caso não retorne nenhum usuario.
    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
