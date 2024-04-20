package com.example.usuarioapi.infrastructure.controller.impl;

import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.domain.model.repositories.IUserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IUserRepository iUsuarioRepository;

    @Test
    void consultarUsuarios() {
    }

    @Test
    void saveUser() throws Exception {
        //given
        UserDTO userDTO = UserDTO.builder()
                .id("123e4567-e89b-12d3-a456-426655440000")
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();
   
        //when
        ResponseEntity<UserDTO> respuesta = testRestTemplate.postForEntity("http://localhost:8080/saveUser", userDTO, UserDTO.class);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());


    }

    @Test
    void updateUser() {
    }
}