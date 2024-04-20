package com.example.usuarioapi.domain.model.repositories;

import com.example.usuarioapi.domain.model.dto.UserDTO;

import java.util.List;

public interface IUserRepository {

    List<UserDTO> findAll();

    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO disableUser(String id);
}
