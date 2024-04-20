package com.example.usuarioapi.infrastructure.controller.impl;

import com.example.usuarioapi.application.service.IUsuarioService;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.infrastructure.controller.IUsuarioController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class UsuarioController implements IUsuarioController {


    private final IUsuarioService service;

    @Autowired
    public UsuarioController(IUsuarioService service) {
        this.service = service;
    }


    @Override
    @GetMapping("/findAll")
    @ApiOperation(value = "Consultar Usuarios Activos", notes = "metodo que consulta usuarios activos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<List<UserDTO>> consultarUsuarios() {

        return ResponseEntity.ok(service.consultarUsuarios());


    }


    @Override
    @PostMapping("/saveUser")
    @ApiOperation(value = "Guardar Usuario", notes = "metodo guarda un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 409, message = "Data enviada ya existe en base de datos", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<UserDTO> saveUser(@ApiParam(type = "String", value =
            "el JSON concepto debe contar con la siguiente estructura:" +
                    "{\n" +
                    "    \"name\": \"Juan Rodriguez\",\n" +
                    "    \"email\": \"juan@rodriguez.org\",\n" +
                    "    \"password\": \"hunter2\",\n" +
                    "    \"phones\": [\n" +
                    "        {\n" +
                    "            \"number\": \"1234567\",\n" +
                    "            \"citycode\": \"1\",\n" +
                    "            \"contrycode\": \"57\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}"
            , required = true) @RequestBody UserDTO json) {
        return ResponseEntity.ok(service.saveUser(json));
    }

    @Override
    @PutMapping("/updateUser")
    @ApiOperation(value = "Actualizar Usuario", notes = "metodo que actualiza un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 409, message = "Data enviada ya existe en base de datos", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<UserDTO> updateUser(UserDTO json) {
        return ResponseEntity.ok(service.updateUser(json));
    }

    @Override
    @PutMapping("/disableUsuer/{idUser}")
    @ApiOperation(value = "Deshabilitar Usuario", notes = "metodo que deshabilita un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 409, message = "Data enviada ya existe en base de datos", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    public ResponseEntity<UserDTO> disableUsuer(@ApiParam(
            value = "idUser", example = "9fc6956c-3555-43cb-818e-ede5e7568634", required = true,
            type = "String") @PathVariable("idUser") String idUser) {
        return ResponseEntity.ok(service.disableUser(idUser));
    }
}
