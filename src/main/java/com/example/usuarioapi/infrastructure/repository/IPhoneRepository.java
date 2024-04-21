package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone, UUID> {
}
