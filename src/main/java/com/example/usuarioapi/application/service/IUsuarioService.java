package com.example.usuarioapi.application.service;


import com.example.usuarioapi.domain.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService {


    List<UserDTO> consultarUsuarios();

    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, UUID idUser);

    UserDTO disableUser(UUID id);
}
