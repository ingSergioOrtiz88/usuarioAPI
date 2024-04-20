package com.example.usuarioapi.infrastructure.repository;


import com.example.usuarioapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);


    List<User> findAllByIsactiveIsTrue();


}
