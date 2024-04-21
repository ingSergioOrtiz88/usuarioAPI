package com.example.usuarioapi.infrastructure.repository;


import com.example.usuarioapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);


    List<User> findAllByIsactiveIsTrue();


}
