package com.example.usuarioapi.domain.model.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {
    private String id;
    private String number;
    private String citycode;
    private String contrycode;

}
