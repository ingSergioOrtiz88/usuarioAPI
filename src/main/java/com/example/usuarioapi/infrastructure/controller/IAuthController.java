package com.example.usuarioapi.infrastructure.controller;

import com.example.usuarioapi.domain.model.dto.LoginDTO;
import com.example.usuarioapi.infrastructure.security.JWTAuthResonseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO);
}
