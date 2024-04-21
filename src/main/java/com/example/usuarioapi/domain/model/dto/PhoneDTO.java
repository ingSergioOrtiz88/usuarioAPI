package com.example.usuarioapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PhoneDTO {
    private UUID id;
    private String number;
    private String citycode;
    private String contrycode;


    private UserDTO user;


}
