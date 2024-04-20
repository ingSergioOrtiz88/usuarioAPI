package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.dto.PhoneDTO;
import com.example.usuarioapi.domain.model.repositories.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepository {

    private final IPhoneRepository iPhoneRepository;
    private final ModelMapper modelMapper;
    @Override
    public PhoneDTO savePhone(PhoneDTO phoneDTO) {

        //modelMapper


        return null;
    }

    @Override
    public PhoneDTO upodatePhone(PhoneDTO phoneDTO) {
        return null;
    }
}
