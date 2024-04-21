package com.example.usuarioapi.infrastructure.repository;


import com.example.usuarioapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);


    List<User> findAllByIsactiveIsTrue();

    Optional<User> findByUsernameOrEmail(String name, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
