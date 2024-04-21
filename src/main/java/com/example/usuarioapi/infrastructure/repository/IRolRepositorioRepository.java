package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRolRepositorioRepository extends JpaRepository<Rol, UUID> {


    Rol findByName(String name);

}
