package com.example.usuarioapi.infrastructure.controller.impl;

import com.example.usuarioapi.application.service.ILoginService;
import com.example.usuarioapi.domain.model.dto.LoginDTO;
import com.example.usuarioapi.infrastructure.controller.IAuthController;
import com.example.usuarioapi.infrastructure.repository.IRolRepositorioRepository;
import com.example.usuarioapi.infrastructure.repository.IUsuarioRepository;
import com.example.usuarioapi.infrastructure.security.JWTAuthResonseDTO;
import com.example.usuarioapi.infrastructure.security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthControllerImpl implements IAuthController {

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


    @PostMapping("/login")
    @ApiOperation(value = "Login Usuario", notes = "metodo que inicia sesion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = JWTAuthResonseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request. por favor valide", response = String.class),
            @ApiResponse(code = 409, message = "Data enviada ya existe en base de datos", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@ApiParam(type = "LoginDTO", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "    \"usernameOrEmail\": \"admin\",\n" +
                    "    \"password\": \"admin\"\n" +
                    "}"
            , required = true) @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);
        iLoginService.updateLoginDate(loginDTO, token);
        return ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }


}

