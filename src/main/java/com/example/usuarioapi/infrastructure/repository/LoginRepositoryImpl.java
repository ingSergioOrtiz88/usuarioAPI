package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.User;
import com.example.usuarioapi.domain.model.dto.LoginDTO;
import com.example.usuarioapi.domain.model.repositories.ILoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoginRepositoryImpl implements ILoginRepository {

    private final ModelMapper modelMapper;

    @Autowired
    private IUsuarioRepository usuarioRepositorio;

    @Override
    public void updateLoginDate(LoginDTO loginDTO, String token) {

        User usuario = usuarioRepositorio.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + loginDTO.getUsernameOrEmail()));
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setToken(token);
        usuario.setModified(LocalDateTime.now());
        usuarioRepositorio.save(usuario);


    }
}
