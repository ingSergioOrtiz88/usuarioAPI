package com.example.usuarioapi.infrastructure.controller;

import com.example.usuarioapi.application.service.ILoginService;
import com.example.usuarioapi.domain.model.Rol;
import com.example.usuarioapi.domain.model.User;
import com.example.usuarioapi.domain.model.dto.LoginDTO;
import com.example.usuarioapi.domain.model.dto.RegistroDTO;
import com.example.usuarioapi.infrastructure.exceptions.GeneralException;
import com.example.usuarioapi.infrastructure.repository.IRolRepositorioRepository;
import com.example.usuarioapi.infrastructure.repository.IUsuarioRepository;
import com.example.usuarioapi.infrastructure.security.JWTAuthResonseDTO;
import com.example.usuarioapi.infrastructure.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository usuarioRepositorio;

    @Autowired
    private IRolRepositorioRepository IRolRepositorioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ILoginService iLoginService;


    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);
        iLoginService.updateLoginDate(loginDTO);
        return ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        try {
            if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
                return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
            }

            if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
                return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
            }

            User usuario = new User();
            usuario.setName(registroDTO.getNombre());
            usuario.setUsername(registroDTO.getUsername());
            usuario.setEmail(registroDTO.getEmail());
            usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

            Rol roles = IRolRepositorioRepository.findByName("ROLE_ADMIN");
            usuario.setRoles(Collections.singleton(roles));
            usuario.setCreated(LocalDateTime.now());
            usuarioRepositorio.save(usuario);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("Erro Guardando un nuevo usuario");
        }

    }
}

