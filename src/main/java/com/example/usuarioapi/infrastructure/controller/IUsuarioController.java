package com.example.usuarioapi.infrastructure.controller;

import com.example.usuarioapi.domain.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface IUsuarioController {


    ResponseEntity<List<UserDTO>> consultarUsuarios();

    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO json);

    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO json, @PathVariable UUID idUser);

    ResponseEntity<UserDTO> disableUsuer(@PathVariable UUID id);


}
