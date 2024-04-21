package com.example.usuarioapi.domain.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String name;
    private UUID id;
    private String email;
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isactive;

    private List<PhoneDTO> phones;


}
