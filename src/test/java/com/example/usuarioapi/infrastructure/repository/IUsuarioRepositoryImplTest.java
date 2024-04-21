package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.Phone;
import com.example.usuarioapi.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class IUsuarioRepositoryImplTest {


    @Autowired
    private IUsuarioRepository usuarioRepository;


    private User user;
    private List<User> listUser;
    private List<Phone> lisPhone;
    private Phone phone;

    @BeforeEach
    void setup() {
        UUID idUsuario = UUID.randomUUID();
        UUID idUsuario2 = UUID.randomUUID();

        user = User.builder()
                .id(idUsuario)
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();

        phone = Phone.builder()
                .id(idUsuario2)
                .citycode("324")
                .contrycode("57")
                .number("43534545")
                .build();
        listUser = new ArrayList<>();
        lisPhone = new ArrayList<>();
        lisPhone.add(phone);
        listUser.add(user);
        user.setPhones(lisPhone);


    }


    @Test
    @DisplayName("Test para guardar un usuario")
    void saveUser() {
        //given
        UUID idUsuario = UUID.randomUUID();

        User userTest = User.builder()
                .id(idUsuario)
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();

        //when
        User userSaved = usuarioRepository.save(userTest);
        //then
        assertThat(userSaved).isNotNull();
    }


    @Test
    @DisplayName("Test para consultar todos los usuarios")
    void findAll() {
        //given
        UUID idUsuario = UUID.randomUUID();

        User userTest = User.builder()
                .id(idUsuario)
                .email("correoaaa@hot.com")
                .password("234234!")
                .name("Sergio")
                .isactive(true)
                .created(LocalDateTime.now())
                .build();
        userTest.setPhones(lisPhone);
        user.setPhones(lisPhone);
        usuarioRepository.save(userTest);
        usuarioRepository.save(user);
        //when
        List<User> User = usuarioRepository.findAllByIsactiveIsTrue();
        //then
        assertThat(User).isNotEmpty();
    }


    @Test
    @DisplayName("Test para actualizar un usuario")
    void updateUser() {

        User userTest = User.builder()
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();
        usuarioRepository.save(userTest);
        User userSaved = null;
        // given(usuarioRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));

        Optional<User> userOptional = usuarioRepository.findById(userTest.getId());

        if (userOptional.isPresent()) {
            userSaved = userOptional.get();
            userSaved.setEmail("segundo@correo.com");
            userSaved.setName("pepito perez");

        }
        User userUpdate = usuarioRepository.save(userSaved);

        //then
        assertThat(userUpdate.getEmail()).isEqualTo("segundo@correo.com");
        assertThat(userUpdate.getName()).isEqualTo("pepito perez");
    }

}