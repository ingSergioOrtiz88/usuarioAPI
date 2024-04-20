package com.example.usuarioapi.infrastructure.controller.impl;

import com.example.usuarioapi.application.service.impl.UsuarioService;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
class UsuarioControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

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
        String jsonUsuarioDTO = objectMapper.writeValueAsString(userDTO);

        //when
        ResultActions response = mockMvc.perform(post("/saveUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .with(request -> {
                            request.setContent(userDTO.toString().getBytes(StandardCharsets.UTF_8));
                            return request;
                        }

                ));

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(userDTO.getName())))
                .andExpect(jsonPath("$.email", is(userDTO.getEmail())));

    }

    @Test
    void updateUser() {
    }
}