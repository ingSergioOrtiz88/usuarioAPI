package com.example.usuarioapi.application.service;


import com.example.usuarioapi.domain.model.dto.UserDTO;

import java.util.List;

public interface IUsuarioService {


    List<UserDTO> consultarUsuarios();

    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO disableUser(String id);
}
