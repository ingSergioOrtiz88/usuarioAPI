package com.example.usuarioapi.application.service.impl;

import com.example.usuarioapi.application.service.IUsuarioService;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.domain.model.repositories.IUserRepository;
import com.example.usuarioapi.domain.model.repositories.PhoneRepository;
import com.example.usuarioapi.infrastructure.exceptions.GeneralException;
import com.example.usuarioapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {


    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private Environment env;

    @Override
    public List<UserDTO> consultarUsuarios() {
        log.info("Se consultan todos");
        return iUserRepository.findAll();
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {


        if (userDTO.getName() == null) {
            log.error("El nombre es obligatorio");
            throw new GeneralException("El nombre es obligatorio");

        }
        if (!Validation.validateEmail(userDTO.getEmail())) {
            log.error("Digite un correo valido");
            throw new GeneralException("Digite un correo valido");

        }
/*        if (!Validation.isValidPassword(userDTO.getPassword(), env.getProperty("password.regex"))) {
            log.error("Digite contraseña valida");
            throw new GeneralException("Error Validando contraseña, debe tener: Mínimo 6 caracteres: " +
                    "Al menos un carácter alfanumérico (letra o número). " +
                    "Al menos un carácter especial (como $, @, !, %, *, ? o &).");

        }


 */
        userDTO.getPhones().forEach(x -> {

            UUID idPhone = UUID.randomUUID();
            x.setId(String.valueOf(idPhone));
        });

        userDTO = iUserRepository.saveUser(userDTO);
        log.info("Se guarda exitoso el usuario");
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        if (userDTO.getName() == null) {
            throw new GeneralException("El nombre es obligatorio");

        }
        if (!Validation.validateEmail(userDTO.getEmail())) {
            throw new GeneralException("Digite un correo valido");

        }
        log.info("Se actualiza");
        return iUserRepository.updateUser(userDTO);
    }

    @Override
    public UserDTO disableUser(String id) {
        return iUserRepository.disableUser(id);
    }


}
