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

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class IUserRepositoryImplTest {


    @Autowired
    private IUserRepository usuarioRepository;


    private User user;
    private List<User> listUser;
    private Phone phone;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id("123e4567-e89b-12d3-a456-426655440000")
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .created(LocalDateTime.now())
                .isactive(true)
                .build();

        phone = Phone.builder()
                .id("123e4567-e89b-12d3-a456-426655440555")
                .citycode("324")
                .contrycode("57")
                .build();
        listUser = new ArrayList<>();
        listUser.add(user);

    }


    @Test
    @DisplayName("Test para guardar un usuario")
    void saveUser() {
        //given
        User userTest = User.builder()
                .id("123e4567-e89b-12d3-a456-426655440000")
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
        User userTest = User.builder()
                .id("123e4567-e89b-12d3-a456-426655440000")
                .email("correo@hot.com")
                .password("234234!")
                .name("Sergio")
                .isactive(true)
                .created(LocalDateTime.now())
                .build();
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
        usuarioRepository.save(user);
        User userSaved = null;
        //given(usuarioRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));
        Optional<User> userOptional = usuarioRepository.findById(user.getId());
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