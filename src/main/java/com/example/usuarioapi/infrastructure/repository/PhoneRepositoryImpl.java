package com.example.usuarioapi.infrastructure.repository;

import com.example.usuarioapi.domain.model.Phone;
import com.example.usuarioapi.domain.model.dto.PhoneDTO;
import com.example.usuarioapi.domain.model.repositories.PhoneRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepositoryInterface {

    private final IPhoneRepository iPhoneRepository;
    private final ModelMapper modelMapper;


    @Override
    public PhoneDTO savePhone(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        modelMapper.map(phoneDTO, phone);

        iPhoneRepository.save(phone);
        modelMapper.map(phone, phoneDTO);
        return phoneDTO;
    }

    @Override
    public PhoneDTO upodatePhone(PhoneDTO phoneDTO) {
        return null;
    }
}
