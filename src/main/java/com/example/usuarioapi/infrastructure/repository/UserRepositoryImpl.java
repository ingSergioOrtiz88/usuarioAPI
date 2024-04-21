package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.Phone;
import com.example.usuarioapi.domain.model.User;
import com.example.usuarioapi.domain.model.dto.UserDTO;
import com.example.usuarioapi.domain.model.repositories.IUserRepository;
import com.example.usuarioapi.infrastructure.exceptions.DataAlreadyExistsException;
import com.example.usuarioapi.infrastructure.exceptions.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements IUserRepository {


    private final ModelMapper modelMapper;


    private final IUsuarioRepository repository;

    private final IPhoneRepository iPhoneRepository;

    @Override
    public List<UserDTO> findAll() {

        List<UserDTO> listUserDto = new ArrayList<>();
        List<User> listUser = repository.findAllByIsactiveIsTrue();
        listUser.forEach(x -> {
            UserDTO userDTO = new UserDTO();
            modelMapper.map(x, userDTO);
            listUserDto.add(userDTO);
        });
        log.info("Se consultan todos exitosamente");

        return listUserDto;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User usuario = new User();
        modelMapper.map(userDTO, usuario);

        try {

            if (repository.findByEmail(usuario.getEmail()) == null) {

                usuario.setCreated(LocalDateTime.now());
                usuario.setIsactive(true);
                repository.save(usuario);
                List<Phone> listPhone = usuario.getPhones();
                List<Phone> listPhonenew = new ArrayList<>();

                listPhone.forEach(x -> {
                    x.setUsuario(usuario);
                    iPhoneRepository.save(x);
                    listPhonenew.add(x);
                });
                usuario.setPhones(listPhonenew);

                modelMapper.map(usuario, userDTO);


            } else {
                throw new DataAlreadyExistsException("El correo electrónico ya está registrado.");

            }
            log.info("Se Guarda exitosamente");
            return userDTO;
        } catch (GeneralException e) {
            throw new GeneralException("Error Guardando el usuario");
        }


    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User usuario = new User();
        modelMapper.map(userDTO, usuario);
        if (repository.findById(usuario.getId()).isPresent()) {
            usuario.setModified(LocalDateTime.now());
            repository.save(usuario);

            List<Phone> listPhone = usuario.getPhones();
            List<Phone> listPhonenew = new ArrayList<>();

            listPhone.forEach(x -> {
                x.setUsuario(usuario);
                iPhoneRepository.save(x);
                listPhonenew.add(x);
            });
            usuario.setPhones(listPhonenew);

        } else {

            throw new GeneralException("el usuario no existe, por favor primero guarde el usuario");
        }
        modelMapper.map(usuario, userDTO);

        log.info("Se Actualiza exitosamente");
        return userDTO;
    }

    @Override
    public UserDTO disableUser(String id) {
        UserDTO userDTO = new UserDTO();

        Optional<User> userOptional = repository.findById(UUID.fromString(id));
        if (userOptional.isPresent()) {
            User usuario = userOptional.get();
            usuario.setIsactive(false);
            usuario.setModified(LocalDateTime.now());
            repository.save(usuario);
            modelMapper.map(usuario, userDTO);
            log.info("Se cambia de estado exitosamente");
            return userDTO;
        } else {
            log.error("Error Cambiando el estado");
            throw new GeneralException("el usuario no existe, por favor primero guarde el usuario");
        }


    }
}
