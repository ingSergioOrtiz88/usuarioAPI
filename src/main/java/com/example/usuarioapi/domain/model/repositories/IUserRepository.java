package com.example.usuarioapi.domain.model.repositories;

import com.example.usuarioapi.domain.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {

    List<UserDTO> findAll();

    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, UUID idUser);

    UserDTO disableUser(UUID id);
}
