package com.example.usuarioapi.domain.model.repositories;


import com.example.usuarioapi.domain.model.dto.PhoneDTO;

public interface PhoneRepository {

    PhoneDTO savePhone(PhoneDTO phoneDTO);
    PhoneDTO upodatePhone(PhoneDTO phoneDTO);
}
