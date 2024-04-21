package com.example.usuarioapi.application.service.impl;

import com.example.usuarioapi.domain.model.dto.PhoneDTO;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.infrastructure.exceptions.GeneralException;
import com.example.usuarioapi.infrastructure.repository.UserRepositoryImpl;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UserRepositoryImpl userRepositoryImpl;


    @Mock
    private Environment env;


    private UserDTO user;
    private List<UserDTO> listUser;
    private PhoneDTO phone;


    @BeforeEach
    void setup() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        user = UserDTO.builder()
                .id(id1)
                .email("correo@hot.com")
                .password("234as234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();

        phone = PhoneDTO.builder()
                .id(id2)
                .citycode("324")
                .number("435345")
                .contrycode("57")
                .build();
        user.setPhones(Arrays.asList(phone));
        listUser = new ArrayList<>();
        listUser.add(user);

    }

    @Test
    @DisplayName("Test para listar a los usuarios")
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
    @DisplayName("Test para validar Nombre Usuario")
    void saveUserNotName() {

        //given
        this.user.setName(null);
        //when
        assertThrows(GeneralException.class, () -> {
            usuarioService.saveUser(user);
        });

    }

    @Test
    @DisplayName("Test para validar Correo")
    void saveUserNotEmail() {

        //given
        this.user.setEmail("234234.com");
        //when
        assertThrows(GeneralException.class, () -> {
            usuarioService.saveUser(user);
        });

    }

    @Test
    @DisplayName("Test para validar Contraseña en el guardado")
    void saveUserNotPassword() {

        //given
        this.user.setPassword("asdasd");
        given(env.getProperty("password.regex")).willReturn("^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$");
        //when
        assertThrows(GeneralException.class, () -> {
            usuarioService.saveUser(user);
        });

    }

    @Test
    @DisplayName("Test para validar Contraseña en el actualizar")
    void updateUserNotPassword() {

        //given
        this.user.setPassword("asdasd");
        given(env.getProperty("password.regex")).willReturn("^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$");
        //when
        assertThrows(GeneralException.class, () -> {
            usuarioService.updateUser(user);
        });

    }

    @Test
    @DisplayName("Test para actualizar un usuario")
    void updateUser() {

        given(userRepositoryImpl.updateUser(user)).willReturn(user);
        given(env.getProperty("password.regex")).willReturn("^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$");
        user.setName("juan perezzzz");
        //when
        UserDTO userDTO = usuarioService.updateUser(user);
        //then
        assertThat(userDTO.getName()).isEqualTo("juan perezzzz");

    }

    @Test
    @DisplayName("Test para actualizar Sin nombre")
    void updateUserNotName() {

        user.setName(null);
        //when

        assertThrows(GeneralException.class, () -> {
            usuarioService.updateUser(user);
        });

    }

    @Test
    @DisplayName("Test para actualizar usuario correo Invalido")
    void updateUserNotemail() {

        user.setEmail("sdasd.com");
        //when

        assertThrows(GeneralException.class, () -> {
            usuarioService.updateUser(user);
        });

    }

    @Test
    @DisplayName("Test para actualizar usuario contraseña Invalido")
    void updateUserNotpassword() {

        user.setPassword("sdasdas");
        given(env.getProperty("password.regex")).willReturn("^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$");
        //when

        assertThrows(GeneralException.class, () -> {
            usuarioService.updateUser(user);
        });

    }

}