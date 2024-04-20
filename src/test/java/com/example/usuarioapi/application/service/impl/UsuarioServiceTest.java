package com.example.usuarioapi.application.service.impl;

import com.example.usuarioapi.domain.model.dto.PhoneDTO;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.infrastructure.repository.IUserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private IUserRepositoryImpl userRepositoryImpl;

    @Mock
    private Environment env;


    private UserDTO user;
    private List<UserDTO> listUser;
    private PhoneDTO phone;


    @BeforeEach
    void setup() {
        user = UserDTO.builder()
                .id("123e4567-e89b-12d3-a456-426655440000")
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();

        phone = PhoneDTO.builder()
                .id("123e4567-e89b-12d3-a456-426655440555")
                .citycode("324")
                .number("435345")
                .contrycode("57")
                .build();
        user.setPhones(Arrays.asList(phone));
        listUser = new ArrayList<>();
        listUser.add(user);

    }

    @Test
    @DisplayName("Test para listar a los empleados")
    void consultarUsuarios() {
        given(userRepositoryImpl.findAll()).willReturn(listUser);

        List<UserDTO> userDTOnew = usuarioService.consultarUsuarios();
        assertThat(userDTOnew).isNotNull();
    }

    @Test
    @DisplayName("Test para guardar un usuario")
    void saveUser() {
        //given
        given(userRepositoryImpl.saveUser(user)).willReturn(user);
        given(env.getProperty("password.regex")).willReturn("^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$");
        //when
        UserDTO userDTO = usuarioService.saveUser(user);
        //then
        assertThat(userDTO).isNotNull();
    }

    @Test
    @DisplayName("Test para actualizar un usuario")
    void updateUser() {

        given(userRepositoryImpl.updateUser(user)).willReturn(user);

        user.setName("juan perezzzz");
        //when
        UserDTO userDTO = usuarioService.updateUser(user);
        //then
        assertThat(userDTO.getName()).isEqualTo("juan perezzzz");

    }


}